package com.james.userservice.controller;

import com.james.userservice.dto.JobApplicationRequest;
import com.james.userservice.facade.UserFacade;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.ProfileResponse;
import com.james.userservice.resquest.*;
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

  @GetMapping
  @Operation(tags = {"User APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_USER')")
  public BaseResponse<ProfileResponse> getProfile() {
    return this.userFacade.getProfile();
  }

  @PostMapping("/invite")
  @Operation(tags = {"User APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_USER')")
  public BaseResponse<Void> inviteWatchingMovie(@RequestBody InviteWatchingMovieRequest request) {
    this.userFacade.inviteWatchingMovie(request);
    return BaseResponse.ok();
  }

  @PatchMapping("/location")
  @Operation(tags = {"User APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_USER')")
  public BaseResponse<Void> changeLocation(@RequestBody ChangeLocationRequest request) {
    this.userFacade.changeLocation(request);
    return BaseResponse.ok();
  }

  @PostMapping("/employee")
  @Operation(tags = {"User APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_USER')")
  public BaseResponse<Void> jobApplication(@RequestBody JobApplicationRequest request) {
    this.userFacade.jobApplication(request);
    return BaseResponse.ok();
  }

  @PatchMapping("/role/{id}")
  @Operation(tags = {"User APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> changeRole(
      @PathVariable Long id, @RequestBody ChangeRoleRequest request) {
    request.setUserId(id);
    this.userFacade.changeRole(request);
    return BaseResponse.ok();
  }
}
