package com.james.userservice.controller;

import com.james.userservice.facade.WorkPlaceFacade;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.PaginationWorkShiftResponse;
import com.james.userservice.response.TheaterProfileResponse;
import com.james.userservice.response.WorkShiftResponse;
import com.james.userservice.resquest.CheckInWorkShiftRequest;
import com.james.userservice.resquest.CheckOutWorkShiftRequest;
import com.james.userservice.resquest.WorkShiftRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/work-places")
@RequiredArgsConstructor
public class WorkPlaceController {
  private final WorkPlaceFacade workPlaceFacade;

  @GetMapping("/work-shift")
  @Operation(tags = {"Work Place APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public BaseResponse<PaginationWorkShiftResponse<WorkShiftResponse, TheaterProfileResponse>>
      findMyWorkShift(@NonNull WorkShiftRequest request) {
    return workPlaceFacade.findMyWordShift(request);
  }

  @PatchMapping("/check-in")
  @Operation(tags = {"Work Place APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public BaseResponse<Void> checkInWorkShift(@NonNull CheckInWorkShiftRequest request) {
    this.workPlaceFacade.checkInWorkShift(request);
    return BaseResponse.ok();
  }

  @PatchMapping("/check-out")
  @Operation(tags = {"Work Place APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public BaseResponse<Void> checkOutWorkShift(@NonNull CheckOutWorkShiftRequest request) {
    this.workPlaceFacade.checkOutWorkShift(request);
    return BaseResponse.ok();
  }
}
