package com.james.paymentservice.response;

import com.james.paymentservice.dto.WalletDTO;
import com.james.paymentservice.enums.TransactionEnum;
import com.james.paymentservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailResponse {
  private Long id;
  private WalletDTO sourceWalletDTO;
  private WalletDTO destinationWalletDTO;
  private TransactionType transactionType;
  private TransactionEnum transactionStatus;
  private Double amount;
  private Long createdAt;
}
