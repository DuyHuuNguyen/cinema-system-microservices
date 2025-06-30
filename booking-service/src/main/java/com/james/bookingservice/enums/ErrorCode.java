package com.james.bookingservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  BOOKING_NOT_FOUND("1111", "Booking not found");

  private final String code;
  private final String message;
}
