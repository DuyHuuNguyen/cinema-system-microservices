package com.james.paymentservice.exception;

import com.james.paymentservice.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DoubleSpendingException extends RuntimeException {
  private final String errorCode;
  private final String message;

  public DoubleSpendingException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
