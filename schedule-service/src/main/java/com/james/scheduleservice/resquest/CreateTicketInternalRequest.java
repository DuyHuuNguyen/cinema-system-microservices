package com.james.scheduleservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketInternalRequest {
  private Float price;
  private Integer totalSeats;
  private Long scheduleId;
}
