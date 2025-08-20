package com.james.bookingservice.controller;

import com.james.bookingservice.facade.TicketFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.resquest.ChangePriceTicketsRequest;
import com.james.bookingservice.resquest.CreateTicketInternalRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
@RestController
public class TicketController {
  private final TicketFacade ticketFacade;

  @PatchMapping("/price")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Booking APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> changeTicketsPrice(@RequestBody ChangePriceTicketsRequest request) {
    this.ticketFacade.changeTicketsPrice(request);
    return BaseResponse.ok();
  }

  @Hidden
  @PostMapping(
      value = "/internal",
      headers = {"secret-key=schedule-service-23130075"})
  public BaseResponse<Void> createTicketInternal(@RequestBody CreateTicketInternalRequest request) {
    this.ticketFacade.createTicketInternal(request);
    return BaseResponse.ok();
  }

  @Hidden
  @DeleteMapping(
      value = "/internal/{id}",
      headers = {"secret-key=schedule-service-23130075"})
  public BaseResponse<Void> deleteTicketById(@PathVariable Long id) {
    this.ticketFacade.deleteById(id);
    return BaseResponse.ok();
  }
}
