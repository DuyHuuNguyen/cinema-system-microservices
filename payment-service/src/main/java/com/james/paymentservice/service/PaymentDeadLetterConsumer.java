package com.james.paymentservice.service;

import com.james.paymentservice.dto.TransactionDeadLetterDTO;

public interface PaymentDeadLetterConsumer {
  void handleFailTransaction(TransactionDeadLetterDTO transactionDeadLetterDTO);
}
