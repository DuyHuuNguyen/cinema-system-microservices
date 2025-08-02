package com.james.scheduleservice.resquest;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpsertScheduleRequest {
  @Hidden private Long id;
  private Long startedAt;
  private Long finishedAt;
  private Long roomId;
  private Long movieId;
  private Long theaterId;

  public void attachId(Long id) {
    this.id = id;
  }
}
