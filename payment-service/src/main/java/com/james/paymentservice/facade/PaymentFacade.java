package com.james.paymentservice.facade;

import com.james.paymentservice.resquest.UpsertPaymentRequest;

public interface PaymentFacade {
  Long createPayment(UpsertPaymentRequest request);

  Boolean verifyPayment(Long id);

  void addBookingIdForPayment(Long id, Long bookingId);
}
