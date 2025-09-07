package com.james.paymentservice.resquest;

import com.james.paymentservice.enums.TransactionType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class TransactionFailNotificationRequest {
  private Long principalId;
  private Long sourceWalletId;
  private Long destinationWalletId;
  private Double amount;
  private TransactionType transactionType;
}
