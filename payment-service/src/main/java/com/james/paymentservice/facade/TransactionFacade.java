package com.james.paymentservice.facade;

import com.james.paymentservice.resquest.CreateTransactionRequest;

public interface TransactionFacade {
  void createTransaction(CreateTransactionRequest ticketTransactionRequest);
}
