package com.james.paymentservice.facade.impl;

import com.james.paymentservice.facade.PaymentFacade;
import com.james.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade {
  private PaymentService paymentService;
}
