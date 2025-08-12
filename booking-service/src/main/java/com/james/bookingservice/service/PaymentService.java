package com.james.bookingservice.service;

import com.james.bookingservice.resquest.CreatePaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service")
public interface PaymentService {

  @PostMapping(value = "/api/v1/payments/internal", headers = "booking-service")
  Boolean createPayment(@RequestBody CreatePaymentRequest request);

  @PostMapping(value = "/api/v1/payments/internal/verify/{id}")
  Boolean verifyPayment(
      @PathVariable Long id, @RequestParam Long theaterId, @RequestParam Long bookingId);
}
