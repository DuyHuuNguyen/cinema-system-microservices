package com.james.paymentservice.dto;

import com.james.paymentservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TransactionCreateDTO {
  private Long principalId;
  private Long sourceWalletId;
  private Long destinationWalletId;
  private Double amount;
  private TransactionType transactionType;
}
