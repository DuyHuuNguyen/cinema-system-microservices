package com.james.paymentservice.service;

import com.james.paymentservice.entity.Payment;

public interface PaymentService {
  void save(Payment payment);
}
