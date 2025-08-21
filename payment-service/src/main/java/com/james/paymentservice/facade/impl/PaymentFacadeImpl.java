package com.james.paymentservice.facade.impl;

import com.james.paymentservice.dto.PaymentDTO;
import com.james.paymentservice.entity.Payment;
import com.james.paymentservice.enums.ErrorCode;
import com.james.paymentservice.exception.EntityNotFoundException;
import com.james.paymentservice.facade.PaymentFacade;
import com.james.paymentservice.resquest.UpsertPaymentRequest;
import com.james.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade {
  private final PaymentService paymentService;

  @Override
  @Transactional
  public Long createPayment(UpsertPaymentRequest request) {
    var payment =
        Payment.builder()
            .paymentStatus(request.getPaymentStatus())
            .paymentType(request.getPaymentType())
            .price(request.getPrice())
            .bookingId(request.getBookingId())
            .build();
    log.info("req {}", request);
    log.info("entity {}", payment);
    var newPayment = this.paymentService.save(payment);
    return newPayment.getId();
  }

  @Override
  public Boolean verifyPayment(Long id) {
    var payment =
        this.paymentService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
    return payment.isCompletedPayment();
  }

  @Override
  @Transactional
  public void addBookingIdForPayment(Long id, Long bookingId) {
    var payment =
        this.paymentService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
    var isValidPayment = payment.getBookingId() == null || !payment.getBookingId().equals(0L);
    if (!isValidPayment) throw new EntityNotFoundException(ErrorCode.PAYMENT_INVALID);
    payment.addBookingId(bookingId);
    this.paymentService.save(payment);
  }

  @Override
  public PaymentDTO findPaymentById(Long id) {
    var payment =
        this.paymentService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
    return PaymentDTO.builder()
        .id(payment.getId())
        .paymentStatus(payment.getPaymentStatus())
        .paymentType(payment.getPaymentType())
        .price(payment.getPrice())
        .build();
  }
}
