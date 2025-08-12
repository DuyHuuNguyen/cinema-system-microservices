package com.james.bookingservice.exception;

import com.james.bookingservice.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TicketExpireException extends RuntimeException {
  private String errorCode;
  private String message;

  public TicketExpireException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
