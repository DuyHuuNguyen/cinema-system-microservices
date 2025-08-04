package com.james.scheduleservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScheduleResponse {
  private Long id;
  private Long startedAt;
  private Long finishedAt;
  private Long roomId;
  private String roomName;
}
