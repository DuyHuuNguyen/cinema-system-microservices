package com.james.notificationservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  NOTIFICATION_NOT_FOUND("1111", "Notification not found");

  private final String code;
  private final String message;
}
