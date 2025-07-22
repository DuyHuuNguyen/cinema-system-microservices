package com.james.scheduleservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  PAYMENT_NOT_FOUND("3331", "Notification not found"),
  SCHEDULE_NOT_FOUND("3332", "Schedule not found"),
  THEATER_NOT_FOUND("3333", "Theater not found"),
  LOCATION_INVALID("3334", "Location invalid");

  private final String code;
  private final String message;
}
