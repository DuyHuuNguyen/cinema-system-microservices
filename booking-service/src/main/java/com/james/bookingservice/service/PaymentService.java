package com.james.bookingservice.service;

import com.james.bookingservice.resquest.CreatePaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment-service")
public interface PaymentService {

  @PostMapping(value = "/api/v1/payments/internal", headers = "secret-key=booking-service")
  Long createPayment(@RequestBody CreatePaymentRequest request);

  @PostMapping(
      value = "/api/v1/payments/internal/verify/{id}",
      headers = "secret-key=booking-service")
  Boolean verifyPayment(@PathVariable Long id);

  @PatchMapping(value = "/api/v1/payments/internal/{id}", headers = "secret-key=booking-service")
  Boolean addBookingIdForPayment(@PathVariable Long id, @RequestParam Long paymentId1);
}
