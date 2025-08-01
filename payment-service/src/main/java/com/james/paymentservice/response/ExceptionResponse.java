package com.james.paymentservice.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {
  private final String errorCode;
  private final String message;
}
