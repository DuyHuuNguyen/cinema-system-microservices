package com.james.paymentservice.resquest;

import com.james.paymentservice.enums.TransactionEnum;
import com.james.paymentservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CreateTransactionInternalForBookingTicket {
  private Long sourceWalletId;
  private Long destinationWalletId;
  private Double amount;
  private TransactionType transactionType;
  private TransactionEnum transactionStatus;
  private Long bookingId;
}
