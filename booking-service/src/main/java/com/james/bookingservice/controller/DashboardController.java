package com.james.bookingservice.controller;

import com.james.bookingservice.facade.DashBoardFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingDetailResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.resquest.BookingCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard-bookings")
@RestController
public class DashboardController {
  private final DashBoardFacade dashBoardFacade;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Dashboard APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<PaginationResponse<BookingResponse>> findByFilter(
      @NonNull BookingCriteria criteria) {
    return this.dashBoardFacade.findByFilter(criteria);
  }

  @GetMapping("/booking-detail/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Dashboard APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<BookingDetailResponse> findDetailById(@PathVariable Long id) {
    return this.dashBoardFacade.findBookingDetailById(id);
  }
}
