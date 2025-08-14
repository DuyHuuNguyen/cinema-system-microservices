package com.james.paymentservice.controller;

import com.james.paymentservice.facade.PaymentFacade;
import com.james.paymentservice.resquest.UpsertPaymentRequest;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentFacade paymentFacade;

  @Hidden
  @PostMapping(
      value = "/internal",
      headers = {"secret-key=booking-service"},
      consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public Long createPayment(@RequestBody UpsertPaymentRequest request) {
    return this.paymentFacade.createPayment(request);
  }

  @Hidden
  @PostMapping(value = "/internal/verify/{id}", headers = "secret-key=booking-service")
  public Boolean verifyPayment(@PathVariable Long id) {
    return this.paymentFacade.verifyPayment(id);
  }

  @Hidden
  @PatchMapping(value = "/internal/{id}", headers = "secret-key=booking-service")
  Boolean addBookingIdForPayment(@PathVariable Long id, @RequestParam Long bookingId) {
    this.paymentFacade.addBookingIdForPayment(id, bookingId);
    return true;
  }
}
