package com.james.paymentservice.service.impl;

import com.james.paymentservice.entity.Payment;
import com.james.paymentservice.reporitory.PaymentRepository;
import com.james.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  private final PaymentRepository paymentRepository;

  @Override
  public void save(Payment payment) {
    this.paymentRepository.save(payment);
  }
}
