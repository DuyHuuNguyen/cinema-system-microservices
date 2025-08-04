package com.james.scheduleservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
  private Long Id;
  private Long theaterId;
  private String roomName;
  private Integer monitorWidth;
  private Integer monitorHeight;
  private Integer totalSeatNumber;
}
