package com.james.scheduleservice.resquest;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoScheduleRequest {
  @Schema(defaultValue = "1")
  private Long theaterId;

  @Schema(defaultValue = "[11,12,13,14,15,16,17]")
  private List<Long> movieIds;

  @Schema(defaultValue = "[1,2,3,4,5,6]")
  private List<Long> roomIds;

  //  @Schema(defaultValue = "1753453200000")
  private LocalDate createdAt;

  @Schema(defaultValue = "1753491600000")
  private Long startedAt;

  @Schema(defaultValue = "1753506000000")
  private Long endAt;

  @Schema(defaultValue = "600000")
  private Long middleSection;

  @Schema(defaultValue = "true")
  private Boolean isDemoSchedule;
}
