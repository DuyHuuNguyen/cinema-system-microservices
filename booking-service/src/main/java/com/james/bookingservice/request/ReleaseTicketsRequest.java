package com.james.bookingservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseTicketsRequest {
  @Schema(defaultValue = "1")
  private Long theaterId;

  @Schema(defaultValue = "77")
  private Long scheduleId;

  @Schema(defaultValue = "[]")
  private List<Long> ticketIds;
}
