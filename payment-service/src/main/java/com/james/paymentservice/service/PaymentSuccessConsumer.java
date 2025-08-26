package com.james.paymentservice.service;

import com.james.paymentservice.dto.TransactionSuccessfulDTO;

public interface PaymentSuccessConsumer {
  void handleSuccessTransaction(TransactionSuccessfulDTO transactionSuccessfulDTO);
}
