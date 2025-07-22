package com.james.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
  private String name;
  private Integer totalSeatNumber;
  private Integer monitorWidth;
  private Integer monitorHeight;
}
