package com.james.userservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  PAYMENT_NOT_FOUND("1111", "Notification not found"),
  EMAIL_IS_USED("1112", "Email is used"),
  ROLE_NOT_FOUND("1113", "Role not found");

  private final String code;
  private final String message;
}
