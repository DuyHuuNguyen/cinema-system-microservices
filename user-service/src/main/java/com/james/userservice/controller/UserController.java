package com.james.userservice.controller;

import com.james.userservice.facade.UserFacade;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.resquest.SignUpUserRequest;
import com.james.userservice.resquest.UpdateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserFacade userFacade;

  @PostMapping("/sign-up")
  @Operation(tags = {"User APIs"})
  public BaseResponse<Void> signUp(@RequestBody SignUpUserRequest upsertUserRequest) {
    this.userFacade.signUp(upsertUserRequest);
    return BaseResponse.ok();
  }

  @PutMapping()
  @Operation(tags = {"User APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_USER')")
  public BaseResponse<Void> updateProfile(@RequestBody UpdateUserRequest request) {
    this.userFacade.updateProfile(request);
    return BaseResponse.ok();
  }
}
