package com.james.scheduleservice.interceptor;

import com.james.scheduleservice.config.SecurityUserDetails;
import com.james.scheduleservice.service.AuthService;
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
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationTokenProviderInterceptor extends OncePerRequestFilter {
  private final AuthService authService;

  private static final List<String> SWAGGER_URLS = List.of("/swagger-ui/", "/v3/api-docs");
  private static final List<String> PUBLIC_URLS =
      List.of("/api/v1/schedules/internal/", "/api/v1/theaters/internal/");
  private final String ROLE_PATTERN = "ROLE_%s";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String path = request.getRequestURI();
    log.info("path : {}", path);
    var isSwagger = SWAGGER_URLS.stream().anyMatch(path::startsWith);
    var isPublicEndpoint = PUBLIC_URLS.stream().anyMatch(path::startsWith);
    log.info("swagger {}", isSwagger);
    log.info("public endpoint {}", isPublicEndpoint);
    String token = getTokenFromHeader(request);

    if (isSwagger || isPublicEndpoint) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      log.info("token {}", token);
      var validTokenDTO = this.authService.validToken(token);
      log.info("{}", validTokenDTO);

      List<GrantedAuthority> authorityList =
          validTokenDTO.getRoles().stream()
              .map(role -> new SimpleGrantedAuthority(String.format(ROLE_PATTERN, role.toString())))
              .collect(Collectors.toList());
      var principle = SecurityUserDetails.build(validTokenDTO.getUserDTO(), authorityList);
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(principle, null, authorityList);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    } catch (Exception e) {
      log.error("Error validating token: {}", e.getMessage(), e);
      SecurityContextHolder.clearContext();
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    filterChain.doFilter(request, response);
  }

  private String getTokenFromHeader(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    if (headerAuth != null) {
      return headerAuth.substring(7);
    }
    return null;
  }
}
