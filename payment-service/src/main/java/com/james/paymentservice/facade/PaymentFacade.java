package com.james.paymentservice.facade;

import com.james.paymentservice.resquest.UpsertPaymentRequest;

public interface PaymentFacade {
  void createPayment(UpsertPaymentRequest request);
}
