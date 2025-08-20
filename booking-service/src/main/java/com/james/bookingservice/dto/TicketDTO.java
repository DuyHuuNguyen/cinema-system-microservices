package com.james.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TicketDTO {
  private Long id;
  private Float price;
  private Integer seatNumber;
  private String seatCode;
  private Long scheduleId;
}
