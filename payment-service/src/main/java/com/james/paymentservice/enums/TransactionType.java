package com.james.paymentservice.enums;

public enum TransactionType {
  DEPOSIT,
  WITHDRAW,
  TRANSFER;

  public Boolean isDepositTransaction() {
    return this == DEPOSIT;
  }

  public Boolean isWithdrawTransaction() {
    return this == WITHDRAW;
  }

  public Boolean isTransferTransaction() {
    return this == TRANSFER;
  }
}
