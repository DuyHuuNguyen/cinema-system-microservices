package com.james.paymentservice.service;

import com.james.paymentservice.entity.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface TransactionService {
  Transaction save(Transaction transaction);

  Optional<Transaction> findById(Long id);

  Page<Transaction> findAll(Specification<Transaction> specification, Pageable pageable);

  List<Transaction> findTransactionBySourceWalletIdAndTimeRange(
      Long sourceWalletId, Long from, Long to);
}
