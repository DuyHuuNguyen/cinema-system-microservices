package com.james.userservice.exception;

import com.james.userservice.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailIsUsedException extends RuntimeException {
  private final String errorCode;
  private final String message;

  public EmailIsUsedException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
