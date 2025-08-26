package com.james.paymentservice.service;

import com.james.paymentservice.entity.Transaction;

public interface TransactionService {
  void save(Transaction transaction);
}
