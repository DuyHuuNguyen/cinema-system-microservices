package com.james.identificationservice.controller;

import com.james.identificationservice.facade.AuthFacade;
import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.request.RefreshTokenRequest;
import com.james.identificationservice.response.BaseResponse;
import com.james.identificationservice.response.LoginResponse;
import com.james.identificationservice.response.RefreshTokenResponse;
import com.james.identificationservice.response.ValidTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

  @PostMapping("refresh-token")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  public BaseResponse<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
    return this.authFacade.refreshToken(request);
  }
}
