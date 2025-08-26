package com.james.paymentservice.service;

import com.james.paymentservice.entity.Transaction;

public interface TransactionService {
  Transaction save(Transaction transaction);
}
