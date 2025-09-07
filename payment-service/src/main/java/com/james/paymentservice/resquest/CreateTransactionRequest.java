package com.james.paymentservice.resquest;

import com.james.paymentservice.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateTransactionRequest {

  @NotNull private String idempotencyKey;

  @NotNull private Long walletId;

  @NotNull private Long partnerId;

  @NotNull private Double amount;

  @NotNull private TransactionType transactionType;
}
