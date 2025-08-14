package com.james.paymentservice.service;

import com.james.paymentservice.entity.Payment;
import java.util.Optional;

public interface PaymentService {
  Payment save(Payment payment);

  Optional<Payment> findById(Long id);

  void addBookingIdForPayment(Long id, Long bookingId);
}
