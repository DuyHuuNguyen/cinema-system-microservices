package com.james.paymentservice.service;

import com.james.paymentservice.dto.TransactionCreateDTO;

public interface CheckinPaymentCreatedProducer {
  void createTransaction(TransactionCreateDTO transactionCreateDTO);
}
