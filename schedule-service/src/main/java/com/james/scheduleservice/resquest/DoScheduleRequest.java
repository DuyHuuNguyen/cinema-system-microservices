package com.james.scheduleservice.resquest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
  @NotNull
  private Long theaterId;

  @Schema(defaultValue = "[11,12,13,14,15,16,17]")
  private List<Long> movieIds;

  @Schema(defaultValue = "[1,2,3,4,5,6]")
  private List<Long> roomIds;

  //  @Schema(defaultValue = "1753453200000")
  @NotNull private LocalDate createdAt;

  @Schema(defaultValue = "49000")
  @NotNull
  @DecimalMin(value = "0.0", inclusive = true, message = "Percent must be >= 0")
  private Float price;

  @Schema(defaultValue = "1753491600000")
  @NotNull
  private Long startedAt;

  @Schema(defaultValue = "1753506000000")
  @NotNull
  private Long endAt;

  @Schema(defaultValue = "600000")
  @NotNull
  private Long middleSection;

  @Schema(defaultValue = "true")
  @NotNull
  private Boolean isDemoSchedule;
}
