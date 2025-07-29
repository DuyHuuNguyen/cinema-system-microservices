package com.james.scheduleservice.response;

import com.james.scheduleservice.dto.LocationDTO;
import com.james.scheduleservice.dto.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScheduleDetailResponse {
  private Long startedAt;
  private Long finishedAt;
  private Long movieId;
  private String moviePoster;
  private String movieName;
  private Long theaterId;
  private String theaterName;
  private LocationDTO theaterLocation;
  private Long roomId;
  private RoomDTO roomDTO;
}
