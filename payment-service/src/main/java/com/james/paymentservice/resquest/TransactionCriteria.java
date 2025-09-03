package com.james.paymentservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCriteria extends BaseCriteria {
  private Long createdAt;
  private Long destinationWalletId;
}
