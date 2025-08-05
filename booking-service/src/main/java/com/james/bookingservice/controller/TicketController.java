package com.james.bookingservice.controller;

import com.james.bookingservice.facade.TicketFacade;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.resquest.CreateTicketInternalRequest;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
@RestController
public class TicketController {
  private final TicketFacade ticketFacade;

  @Hidden
  @PostMapping("/internal")
  public BaseResponse<Void> createTicketInternal(@RequestBody CreateTicketInternalRequest request) {
    this.ticketFacade.createTicketInternal(request);
    return BaseResponse.ok();
  }

  @Hidden
  @DeleteMapping("/internal/{id}")
  public BaseResponse<Void> deleteTicketById(@PathVariable Long id) {
    this.ticketFacade.deleteById(id);
    return BaseResponse.ok();
  }
}
