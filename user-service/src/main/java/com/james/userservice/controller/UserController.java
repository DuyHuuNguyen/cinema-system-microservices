package com.james.userservice.controller;

import com.james.userservice.facade.UserFacade;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.resquest.UpsertUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserFacade userFacade;

  @PostMapping("/sign-up")
  @Operation(tags = {"User APIs"})
  public BaseResponse<Void> signUp(@RequestBody UpsertUserRequest upsertUserRequest) {
    this.userFacade.signUp(upsertUserRequest);
    return BaseResponse.ok();
  }
}
