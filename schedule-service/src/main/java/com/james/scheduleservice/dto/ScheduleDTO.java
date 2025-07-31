package com.james.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ScheduleDTO {
  private Long scheduleId;
  private Long startedAt;
  private Long finishedAt;
  private Long theaterId;
  private String theaterName;
  private LocationDTO locationTheater;
  private MovieDTO movieDTO;
}
