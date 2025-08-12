package com.james.paymentservice.controller;

import com.james.paymentservice.facade.PaymentFacade;
import com.james.paymentservice.resquest.UpsertPaymentRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymentFacade paymentFacade;

  @Hidden
  @PostMapping(value = "/internal", headers = "booking-service")
  public Boolean createPayment(@RequestBody UpsertPaymentRequest request) {
    this.paymentFacade.createPayment(request);
    return true;
  }
}
