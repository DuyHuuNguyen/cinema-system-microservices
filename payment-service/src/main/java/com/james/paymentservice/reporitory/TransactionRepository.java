package com.james.paymentservice.reporitory;

import com.james.paymentservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository
    extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    @Query("""
    SELECT t
    FROM Transaction t
    WHERE t.sourceWallet.id =:sourceWalletId
    AND t.createdAt between :from AND :to
    """)
    List<Transaction> findTransactionBySourceWalletIdAndTimeRange(Long sourceWalletId, Long from, Long to);
}
