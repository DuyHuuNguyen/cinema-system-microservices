package com.james.paymentservice.service.impl;

import com.james.paymentservice.entity.Transaction;
import com.james.paymentservice.reporitory.TransactionRepository;
import com.james.paymentservice.service.*;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;

  @Override
  public Transaction save(Transaction transaction) {
    return this.transactionRepository.save(transaction);
  }

  @Override
  public Optional<Transaction> findById(Long id) {
    return this.transactionRepository.findById(id);
  }

  @Override
  public Page<Transaction> findAll(Specification<Transaction> specification, Pageable pageable) {
    return this.transactionRepository.findAll(specification, pageable);
  }

  @Override
  public List<Transaction> findTransactionBySourceWalletIdAndTimeRange(
      Long sourceWalletId, Long from, Long to) {
    return this.transactionRepository.findTransactionBySourceWalletIdAndTimeRange(
        sourceWalletId, from, to);
  }
}
