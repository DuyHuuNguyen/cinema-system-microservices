package com.james.paymentservice.enums;

public enum TransactionEnum {
  SPENDING,
  SUCCESS,
  FAILURE,
  ERROR;

  public boolean isSuccess() {
    return this == SUCCESS;
  }
}
