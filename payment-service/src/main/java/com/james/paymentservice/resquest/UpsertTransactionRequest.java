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
public class UpsertTransactionRequest {

  @NotNull private String idempotencyKey;

  @NotNull private Long walletId;

  @NotNull private Long partnerId;

  private String bookingCode;

  @NotNull private Double amount;

  private Boolean isPaymentForBooking;

  @NotNull private TransactionType transactionType;
}
