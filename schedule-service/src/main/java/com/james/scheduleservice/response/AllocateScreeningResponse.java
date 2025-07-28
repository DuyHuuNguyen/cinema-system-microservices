package com.james.scheduleservice.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllocateScreeningResponse {
  private Long movieId;
  private String movieName;
  private String firstImage;
  private Long roomId;
  private String roomName;
  private Integer totalSeatNumber;
  private LocalDateTime startedAt;
  private LocalDateTime endedAt;
}
