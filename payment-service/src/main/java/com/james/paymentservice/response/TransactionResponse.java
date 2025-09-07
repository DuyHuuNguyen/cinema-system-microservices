package com.james.paymentservice.response;

import com.james.paymentservice.enums.TransactionEnum;
import com.james.paymentservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionResponse {
  private Long id;
  private TransactionType transactionType;
  private TransactionEnum transactionStatus;
  private Double amount;
  private Long createdAt;
}
