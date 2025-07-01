package com.james.identificationservice.controller;

import com.james.identificationservice.facade.UserFacade;
import com.james.identificationservice.request.LoginRequest;
import com.james.identificationservice.response.BaseResponse;
import com.james.identificationservice.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserFacade userFacade;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(
          summary = "Login to system by email and password",
          tags = {"Auth APIs"})
  public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request) {
    return this.userFacade.login(request);
  }
}
