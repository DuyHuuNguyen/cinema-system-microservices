package com.james.scheduleservice.dto;

import java.time.LocalDateTime;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateScreeningDTO {
  private Long movieId;
  private String movieName;
  private String firstImage;
  private Long roomId;
  private String roomName;
  private Integer totalSeatNumber;
  private LocalDateTime startedAt;
  private LocalDateTime endedAt;
}
