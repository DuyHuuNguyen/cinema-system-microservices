package com.james.bookingservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingResponse {
  private Long id;
  private Long userId;
  private Long paymentId;
  private Long theaterId;
  private String bookingCode;
  private Long createdAt;
}
