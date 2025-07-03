package com.james.identificationservice.facade.Iml;

import com.james.identificationservice.config.SecurityUserDetails;
import com.james.identificationservice.entity.Role;
import com.james.identificationservice.enums.ErrorCode;
import com.james.identificationservice.enums.TokenType;
import com.james.identificationservice.exception.EntityNotFoundException;
import com.james.identificationservice.exception.InvalidTokenException;
import com.james.identificationservice.facade.AuthFacade;
import com.james.identificationservice.mapper.UserMapper;
import com.james.identificationservice.request.AuthenticationRequest;
import com.james.identificationservice.request.AuthorizeRequest;
import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.response.BaseResponse;
import com.james.identificationservice.response.LoginResponse;
import com.james.identificationservice.service.BookingService;
import com.james.identificationservice.service.CacheService;
import com.james.identificationservice.service.JwtService;
import com.james.identificationservice.service.UserService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacadeIml implements AuthFacade {
  private final UserService userService;
  private final CacheService cacheService;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final BookingService bookingService;
  private final UserMapper userMapper;

  private final String ROLE_PATTERN = "ROLE_%s";

  @Override
  public BaseResponse<LoginResponse> login(LoginRequest request) {
    var authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    var accessToken = jwtService.generateAccessToken(request.getEmail());
    var refreshToken = jwtService.generateRefreshToken(request.getEmail());

    var accessTokenCacheKey =
        String.format(TokenType.ACCESS_TOKEN.getCacheKeyTemplate(), request.getEmail());
    var refreshTokenCacheKey =
        String.format(TokenType.REFRESH_TOKEN.getCacheKeyTemplate(), request.getEmail());

    cacheService.store(accessTokenCacheKey, accessToken, 1, TimeUnit.HOURS);
    cacheService.store(refreshTokenCacheKey, refreshToken, 14, TimeUnit.DAYS);

    return BaseResponse.build(
        LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build(), true);
  }

  @Override
  public BaseResponse<Void> authorizeRequest(AuthorizeRequest request) {

    var email = jwtService.getEmailFromJwtToken(request.getAccessToken());

    if (!validateTokenFromCache(email, request.getAccessToken()))
      throw new InvalidTokenException(ErrorCode.TOKEN_NOT_FOUND);

    var user =
        userService
            .findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));

    List<GrantedAuthority> authorityList =
        user.getRoles().stream()
            .map(
                role -> new SimpleGrantedAuthority(String.format(ROLE_PATTERN, role.getRoleName())))
            .collect(Collectors.toList());

    var principle = SecurityUserDetails.build(user, authorityList);
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(principle, null, authorityList);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    var authenticationRequest =
        AuthenticationRequest.builder()
            .roles(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
            .userDTO(this.userMapper.toUserDTO(user))
            .build();

    this.addAuthenticationForAllService(authenticationRequest);

    return BaseResponse.ok();
  }

  private Boolean validateTokenFromCache(String email, String token) {
    if (token == null) return false;

    var accessTokenCacheKey = String.format(TokenType.ACCESS_TOKEN.getCacheKeyTemplate(), email);

    return cacheService.hasKey(accessTokenCacheKey)
        && cacheService.retrieve(accessTokenCacheKey).equals(token);
  }

  private void addAuthenticationForAllService(AuthenticationRequest request) {
    bookingService.addAuthentication(request);
  }
}
