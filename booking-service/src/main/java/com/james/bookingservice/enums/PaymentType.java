package com.james.bookingservice.enums;

public enum PaymentType {
  COD,
  MOMO,
  VN_PAY;

  public Boolean isCashOnDelivery() {
    return this == COD;
  }
}
