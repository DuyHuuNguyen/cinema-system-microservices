package com.james.paymentservice.facade.impl;

import com.james.paymentservice.entity.Payment;
import com.james.paymentservice.facade.PaymentFacade;
import com.james.paymentservice.resquest.UpsertPaymentRequest;
import com.james.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade {
  private PaymentService paymentService;

  @Override
  @Transactional
  public void createPayment(UpsertPaymentRequest request) {
    var payment =
        Payment.builder()
            .paymentStatus(request.getPaymentStatus())
            .paymentType(request.getPaymentType())
            .price(request.getPrice())
            .bookingId(request.getBookingId())
            .build();
    this.paymentService.save(payment);
  }
}
