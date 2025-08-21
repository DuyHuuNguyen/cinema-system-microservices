package com.james.bookingservice.controller;

import com.james.bookingservice.facade.TicketFacade;
import com.james.bookingservice.request.ChangePriceTicketsRequest;
import com.james.bookingservice.request.CreateTicketInternalRequest;
import com.james.bookingservice.request.ReleaseTicketsRequest;
import com.james.bookingservice.request.UpsertTicketRequest;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.TicketResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
@RestController
public class TicketController {
  private final TicketFacade ticketFacade;

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Ticket APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> updateTicket(
      @PathVariable Long id, @RequestBody UpsertTicketRequest request) {
    request.withId(id);
    this.ticketFacade.updateTicket(request);
    return BaseResponse.ok();
  }

  @PatchMapping("/price")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Ticket APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> changeTicketsPrice(@RequestBody ChangePriceTicketsRequest request) {
    this.ticketFacade.changeTicketsPrice(request);
    return BaseResponse.ok();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Ticket APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<List<TicketResponse>> findAllTicket(
      @RequestParam("scheduleId") Long scheduleId) {
    return this.ticketFacade.findAllTicket(scheduleId);
  }

  @PatchMapping("/release-tickets")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Ticket APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> releaseTickets(@RequestBody ReleaseTicketsRequest request) {
    this.ticketFacade.releaseTickets(request);
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
