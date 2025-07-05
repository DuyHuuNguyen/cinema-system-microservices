package com.james.bookingservice.controller;

import com.james.bookingservice.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
  private final AuthFacade authFacade;

  //  @GetMapping("/role-user-ok")
  //  @SecurityRequirement(name = "Bearer Authentication")
  //  @PreAuthorize("isAuthenticated()")
  //  public BaseResponse<String> roleUserOk() {
  //    return BaseResponse.<String>build("ok roi nhe",true);
  //  }
  //
  //  @GetMapping("/role-EMPLOYEE")
  //  @SecurityRequirement(name = "Bearer Authentication")
  //  @PreAuthorize("hasRole('EMPLOYEE')")
  //  public BaseResponse<String> roleUserOkkk() {
  //    return BaseResponse.<String>build("ko code",true);
  //  }

}
