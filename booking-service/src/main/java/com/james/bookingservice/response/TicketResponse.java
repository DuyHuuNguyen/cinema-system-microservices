package com.james.bookingservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TicketResponse {
  private Long id;
  private Float price;
  private Integer seatNumber;
  private Boolean isUsed;
  private String seatCode;
  private Long scheduleId;
  private Long releasedAt;
}
