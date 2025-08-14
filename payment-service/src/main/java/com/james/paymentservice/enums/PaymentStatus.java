package com.james.paymentservice.enums;

public enum PaymentStatus {
  COMPLETED,
  FAILURE;

  public Boolean isCompleted() {
    return this == PaymentStatus.COMPLETED;
  }
}
