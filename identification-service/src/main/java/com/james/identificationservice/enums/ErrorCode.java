package com.james.identificationservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  USER_NOT_FOUND("1111", "User not found"),
  JWT_INVALID("1112", "Invalid jwt token"),
  TOKEN_NOT_FOUND("1112", "Token not found");

  private final String code;
  private final String message;
}
