package com.james.bookingservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ValidScheduleOfTheaterRequest {
  private Long scheduleId;
  private Long theaterId;
}
