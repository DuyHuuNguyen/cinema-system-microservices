package com.james.bookingservice.controller;

import com.james.bookingservice.facade.BookingFacade;
import com.james.bookingservice.request.BookingCriteria;
import com.james.bookingservice.request.CreateBookingTicketRequest;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingDetailResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
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

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Booking APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<BookingResponse>> findByFilter(
      @NonNull BookingCriteria criteria) {
    return this.bookingFacade.findByFilter(criteria);
  }

  @GetMapping("/booking-detail/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Booking APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<BookingDetailResponse> findDetailById(@PathVariable Long id) {
    return this.bookingFacade.findDetailById(id);
  }
}
