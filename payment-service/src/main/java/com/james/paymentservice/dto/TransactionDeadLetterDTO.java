package com.james.paymentservice.dto;

import com.james.paymentservice.enums.TransactionType;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TransactionDeadLetterDTO {
  @Builder.Default private String message = "A Transaction Failed.";
  private Long sourceWalletId;
  private Long destinationWalletId;
  private Double amount;
  private TransactionType transactionType;
}
