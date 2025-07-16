package com.james.identificationservice.exception;

import com.james.identificationservice.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvalidOTPException extends RuntimeException {
  private final String errorCode;
  private final String message;

  public InvalidOTPException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
