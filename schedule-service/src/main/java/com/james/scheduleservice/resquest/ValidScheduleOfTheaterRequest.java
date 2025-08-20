package com.james.scheduleservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidScheduleOfTheaterRequest {
  private Long scheduleId;
  private Long theaterId;
}
