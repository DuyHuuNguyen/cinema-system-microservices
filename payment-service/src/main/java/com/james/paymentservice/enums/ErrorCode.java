package com.james.paymentservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  PAYMENT_NOT_FOUND("1111", "Payment not found"),
  PAYMENT_INVALID("1112", "Payment invalid"),
  ;

  private final String code;
  private final String message;
}
