package com.james.bookingservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  BOOKING_NOT_FOUND("1111", "Booking not found"),
  SCHEDULE_NOT_FOUND("1112", "Schedule not found"),
  TICKET_NOT_FOUND("1113", "Ticket not found"),
  TICKET_IS_USED("1114", "Ticket is used"),
  VOUCHER_IS_EXPIRED("1115", "Voucher is expired"),
  FOOD_NOT_FOUND("1116", "Food not found"),
  PAYMENT_INVALID("1117", "Payment invalid"),
  FOOD_PRICE_NOT_FOUND("1118", "Food price not found"),
  PERMISSION_DENIED("1119", "Permission denied");
  private final String code;
  private final String message;
}
