package com.james.bookingservice.controller;

import com.james.bookingservice.facade.BookingFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.resquest.CreateBookingTicketRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
@RestController
public class BookingController {
  private final BookingFacade bookingFacade;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Booking APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> createBooking(@RequestBody CreateBookingTicketRequest request) {
    this.bookingFacade.createBooking(request);
    return BaseResponse.ok();
  }
}
