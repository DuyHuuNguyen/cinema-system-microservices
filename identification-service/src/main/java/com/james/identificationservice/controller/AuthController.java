package com.james.identificationservice.controller;

import com.james.identificationservice.facade.AuthFacade;
import com.james.identificationservice.request.AuthorizeRequest;
import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.response.BaseResponse;
import com.james.identificationservice.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthFacade userFacade;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Login to system by email and password",
      tags = {"Auth APIs"})
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {
    return this.userFacade.login(request);
  }

  @PostMapping("/authorization")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Auth APIs"})
  public BaseResponse<Void> authorizeRequest(AuthorizeRequest request) {
    return this.userFacade.authorizeRequest(request);
  }
}
