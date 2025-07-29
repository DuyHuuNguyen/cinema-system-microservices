package com.james.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
  private String name;
  private Integer totalSeatNumber;
  private Integer monitorWidth;
  private Integer monitorHeight;
}
