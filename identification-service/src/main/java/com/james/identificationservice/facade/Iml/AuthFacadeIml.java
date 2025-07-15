package com.james.identificationservice.facade.Iml;

import com.james.identificationservice.config.SecurityUserDetails;
import com.james.identificationservice.dto.EmailDTO;
import com.james.identificationservice.entity.Role;
import com.james.identificationservice.enums.ErrorCode;
import com.james.identificationservice.enums.TokenType;
import com.james.identificationservice.exception.EntityNotFoundException;
import com.james.identificationservice.exception.InvalidTokenException;
import com.james.identificationservice.facade.AuthFacade;
import com.james.identificationservice.mapper.UserMapper;
import com.james.identificationservice.request.*;
import com.james.identificationservice.response.*;
import com.james.identificationservice.service.CacheService;
import com.james.identificationservice.service.JwtService;
import com.james.identificationservice.service.ProducerSendEmailService;
import com.james.identificationservice.service.UserService;
import com.james.identificationservice.until.GenerateCodeUntil;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthFacadeIml implements AuthFacade {
  private final UserService userService;
  private final CacheService cacheService;
  private final JwtService jwtService;
  private final ProducerSendEmailService producerSendEmailService;
  private final AuthenticationManager authenticationManager;
  private final UserMapper userMapper;

  private final String ROLE_PATTERN = "ROLE_%s";
  private final String OTP_KEY_PATTERN = "OPT_%s";

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
  public BaseResponse<Void> authorizeRequest(String accessToken) {

    var email = jwtService.getEmailFromJwtToken(accessToken);

    if (!validateTokenFromCache(email, accessToken))
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
    log.info(
        "role {}", Arrays.toString(user.getRoles().stream().map(r -> r.getRoleName()).toArray()));
    var principle = SecurityUserDetails.build(user, authorityList);
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(principle, null, authorityList);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    var authenticationRequest =
        AuthenticationRequest.builder()
            .roles(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
            .userDTO(this.userMapper.toUserDTO(user))
            .build();

    return BaseResponse.ok();
  }

  @Override
  public ValidTokenResponse validToken(String accessToken) {
    var email = jwtService.getEmailFromJwtToken(accessToken);

    if (!validateTokenFromCache(email, accessToken))
      throw new InvalidTokenException(ErrorCode.TOKEN_NOT_FOUND);

    var user =
        userService
            .findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    return ValidTokenResponse.builder()
        .userDTO(this.userMapper.toUserDTO(user))
        .roles(user.getRoles().stream().map(Role::getRoleName).toList())
        .build();
  }

  @Override
  public BaseResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest request) {
    var isValidRefreshToken = this.jwtService.validateToken(request.getRefreshToken());
    if (!isValidRefreshToken) throw new EntityNotFoundException(ErrorCode.JWT_INVALID);

    var email = jwtService.getEmailFromJwtToken(request.getRefreshToken());

    var accessTokenCacheKey = String.format(TokenType.ACCESS_TOKEN.getCacheKeyTemplate(), email);
    var refreshTokenCacheKey = String.format(TokenType.REFRESH_TOKEN.getCacheKeyTemplate(), email);

    var accessToken = cacheService.retrieve(accessTokenCacheKey);
    var refreshToken = cacheService.retrieve(refreshTokenCacheKey);

    if (refreshToken == null) throw new EntityNotFoundException(ErrorCode.JWT_INVALID);

    if (!refreshToken.equals(request.getRefreshToken()))
      throw new EntityNotFoundException(ErrorCode.JWT_INVALID);

    var newAccessToken = jwtService.generateAccessToken(email);
    cacheService.store(accessTokenCacheKey, newAccessToken, 1, TimeUnit.HOURS);

    return BaseResponse.build(
        RefreshTokenResponse.builder()
            .accessToken(newAccessToken)
            .refreshToken(request.getRefreshToken())
            .build(),
        true);
  }

  @Override
  public void logout() {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var accessTokenCacheKey =
        String.format(TokenType.ACCESS_TOKEN.getCacheKeyTemplate(), principal.getUsername());
    var refreshTokenCacheKey =
        String.format(TokenType.REFRESH_TOKEN.getCacheKeyTemplate(), principal.getUsername());

    cacheService.delete(accessTokenCacheKey);
    cacheService.delete(refreshTokenCacheKey);
  }

  @Override
  public void forgotPassword(ForgotPasswordRequest request) {
    var user =
        userService
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    var otp = GenerateCodeUntil.generateOTP();
    String otpKey = String.format(OTP_KEY_PATTERN, request.getEmail());
    cacheService.store(otpKey, otp, 1, TimeUnit.HOURS);
    var emailDTO =
        EmailDTO.builder()
            .toEmail(user.getEmail())
            .body("Your OTP is " + otp)
            .subject("Your OTP")
            .build();
    this.producerSendEmailService.sendEmail(emailDTO);
  }

  @Override
  public BaseResponse<VerifyOTPResponse> verifyOTP(VerifyOTPRequest request) {
    String optKey = String.format(OTP_KEY_PATTERN, request.getEmail());

    var isNotFoundOTP = !this.cacheService.hasKey(optKey);
    if (isNotFoundOTP) throw new EntityNotFoundException(ErrorCode.OTP_NOT_FOUND);

    var otp = this.cacheService.retrieve(optKey);
    var isValidOTP = otp.equals(request.getOtp());

    if (!isValidOTP) throw new InvalidTokenException(ErrorCode.JWT_INVALID);

    var resetPasswordToken = this.jwtService.generateResetPasswordToken(request.getEmail());
    var accessTokenCacheKey =
        String.format(TokenType.ACCESS_TOKEN.getCacheKeyTemplate(), request.getEmail());
    this.cacheService.store(accessTokenCacheKey, resetPasswordToken, 1, TimeUnit.HOURS);

    var verifyTokenResponse =
        VerifyOTPResponse.builder().resetPasswordToken(resetPasswordToken).build();
    return BaseResponse.build(verifyTokenResponse, true);
  }

  private Boolean validateTokenFromCache(String email, String token) {
    if (token == null) return false;

    var accessTokenCacheKey = String.format(TokenType.ACCESS_TOKEN.getCacheKeyTemplate(), email);

    return cacheService.hasKey(accessTokenCacheKey)
        && cacheService.retrieve(accessTokenCacheKey).equals(token);
  }
}
