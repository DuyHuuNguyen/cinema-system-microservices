package com.james.paymentservice.service;

import com.james.paymentservice.dto.TransactionCreateDTO;

public interface CheckoutPaymentConsumer {
  void receiveAndHandleTransaction(TransactionCreateDTO transactionCreateDTO);
}
