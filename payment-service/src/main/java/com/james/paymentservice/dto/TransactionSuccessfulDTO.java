package com.james.paymentservice.dto;

import com.james.paymentservice.enums.TransactionType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class TransactionSuccessfulDTO {
  private Long sourceWalletId;
  private Long destinationWalletId;
  private Double amount;
  private TransactionType transactionType;
}
