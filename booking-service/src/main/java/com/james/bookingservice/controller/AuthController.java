package com.james.bookingservice.controller;

import com.james.bookingservice.facade.AuthFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.resquest.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
  private final AuthFacade authFacade;

  @PostMapping
  public BaseResponse<Void> authentication(
      @RequestBody AuthenticationRequest authenticationRequest) {
    return this.authFacade.authentication(authenticationRequest);
  }
}
