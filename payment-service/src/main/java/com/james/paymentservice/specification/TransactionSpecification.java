package com.james.paymentservice.specification;

import com.james.paymentservice.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecification {
  public static Specification<Transaction> withCreatedAtWithin30Minutes(Long createdAt) {
    return (root, query, criteriaBuilder) -> {
      if (createdAt == null) {
        return criteriaBuilder.conjunction();
      }
      long thirtyMinutesMillis = 30 * 60 * 1000;
      Long lowerBound = createdAt - thirtyMinutesMillis;
      Long upperBound = createdAt + thirtyMinutesMillis;
      return criteriaBuilder.between(root.get("createdAt"), lowerBound, upperBound);
    };
  }

  public static Specification<Transaction> withDestinationWalletId(Long destinationWalletId) {
    return (root, query, cb) -> {
      if (destinationWalletId == null) {
        return cb.conjunction();
      }

      return cb.equal(root.get("destinationWalletId"), destinationWalletId);
    };
  }
}
