package com.james.scheduleservice.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CacheScheduleDTO {
  private LocalDate doScheduledAt;
  private Long theaterId;
}
