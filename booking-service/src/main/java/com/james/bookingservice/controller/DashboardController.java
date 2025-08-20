package com.james.bookingservice.controller;

import com.james.bookingservice.facade.DashBoardFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.resquest.BookingCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
