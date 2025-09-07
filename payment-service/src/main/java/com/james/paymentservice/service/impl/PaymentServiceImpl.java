//package com.james.paymentservice.service.impl;
//
//import com.james.paymentservice.entity.Payment;
//import com.james.paymentservice.reporitory.PaymentRepository;
//import com.james.paymentservice.service.PaymentService;
//import java.util.Optional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class PaymentServiceImpl implements PaymentService {
//  private final PaymentRepository paymentRepository;
//
//  @Override
//  public Payment save(Payment payment) {
//    return this.paymentRepository.save(payment);
//  }
//
//  @Override
//  public Optional<Payment> findById(Long id) {
//    return this.paymentRepository.findById(id);
//  }
//
//  @Override
//  public void addBookingIdForPayment(Long id, Long bookingId) {
//    this.paymentRepository.addBookingIdForPayment(id, bookingId);
//  }
//}
