package com.james.identificationservice.facade.Iml;

import com.james.identificationservice.enums.TokenType;
import com.james.identificationservice.facade.UserFacade;
import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.response.BaseResponse;
import com.james.identificationservice.response.LoginResponse;
import com.james.identificationservice.service.CacheService;
import com.james.identificationservice.service.JwtService;
import com.james.identificationservice.service.UserService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeIml implements UserFacade {
  private final UserService userService;
  private final CacheService cacheService;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

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
}
