package com.james.identificationservice.interceptor;

import com.james.identificationservice.config.SecurityUserDetails;
import com.james.identificationservice.enums.ErrorCode;
import com.james.identificationservice.enums.TokenType;
import com.james.identificationservice.exception.EntityNotFoundException;
import com.james.identificationservice.exception.InvalidTokenException;
import com.james.identificationservice.service.CacheService;
import com.james.identificationservice.service.JwtService;
import com.james.identificationservice.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class AuthTokenInterceptor extends OncePerRequestFilter {

  private final UserService userService;
  private final JwtService jwtService;
  private final CacheService cacheService;

  private static final List<String> PUBLIC_APIS = List.of("/api/v1/auth");

  private static final List<String> SWAGGER_URLS = List.of("/swagger-ui/", "/v3/api-docs/");

  private final String RESET_PASSWORD_URL = "_";

  private final String ROLE_PATTERN = "ROLE_%s";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String path = request.getRequestURI();
    log.info("path : {}", path);
    var isSwagger = SWAGGER_URLS.stream().anyMatch(path::startsWith);
    var isPublic = PUBLIC_APIS.stream().anyMatch(path::startsWith);

    if (isSwagger || isPublic) {
      log.info("Pass request");
      filterChain.doFilter(request, response);
      return;
    }
    String token = getTokenFromHeader(request);
    try {
      if (jwtService.validateToken(token)) {
        var email = jwtService.getEmailFromJwtToken(token);

        if (!validateTokenFromCache(email, token, path))
          throw new InvalidTokenException(ErrorCode.TOKEN_NOT_FOUND);

        var user =
            userService
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

        List<GrantedAuthority> authorityList =
            user.getRoles().stream()
                .map(
                    role ->
                        new SimpleGrantedAuthority(String.format(ROLE_PATTERN, role.getRoleName())))
                .collect(Collectors.toList());

        var principle = SecurityUserDetails.build(user, authorityList);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(principle, null, authorityList);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // setAuthorization for rest services
      }
      filterChain.doFilter(request, response);
    } catch (InvalidTokenException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }

  private String getTokenFromHeader(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    if (headerAuth != null) {
      return headerAuth.substring(7);
    }
    return null;
  }

  private Boolean validateTokenFromCache(String email, String token, String path) {
    if (token == null) return false;

    if (path.startsWith(RESET_PASSWORD_URL)) return true;

    var accessTokenCacheKey = String.format(TokenType.ACCESS_TOKEN.getCacheKeyTemplate(), email);

    return cacheService.hasKey(accessTokenCacheKey)
        && cacheService.retrieve(accessTokenCacheKey).equals(token);
  }
}
