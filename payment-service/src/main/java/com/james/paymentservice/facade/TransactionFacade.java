package com.james.paymentservice.facade;

import com.james.paymentservice.resquest.UpsertTransactionRequest;

public interface TransactionFacade {
  void createTransaction(UpsertTransactionRequest upsertTransactionRequest);
}
