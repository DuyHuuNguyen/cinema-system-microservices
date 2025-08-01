package com.james.identificationservice.controller;

import com.james.identificationservice.facade.AuthFacade;
import com.james.identificationservice.request.*;
import com.james.identificationservice.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthFacade authFacade;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Login to system by email and password",
      tags = {"Auth APIs"})
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {
    return this.authFacade.login(request);
  }

  @PostMapping("/authorization")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  public ValidTokenResponse validToken(@RequestHeader("AccessToken") String accessToken) {
    return this.authFacade.validToken(accessToken);
  }

  @PostMapping("/refresh-token")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  public BaseResponse<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
    return this.authFacade.refreshToken(request);
  }

  @PostMapping("/logout")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> logout() {
    this.authFacade.logout();
    return BaseResponse.ok();
  }

  @PostMapping("/forgot-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  public BaseResponse<Void> forgotPassword(@RequestBody ForgotPasswordRequest request) {
    this.authFacade.forgotPassword(request);
    return BaseResponse.ok();
  }

  @PostMapping("/verify-otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  public BaseResponse<VerifyOTPResponse> verifyOTP(@RequestBody VerifyOTPRequest request) {
    return this.authFacade.verifyOTP(request);
  }

  @PatchMapping("/reset-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
    this.authFacade.resetPassword(request);
    return BaseResponse.ok();
  }
}
