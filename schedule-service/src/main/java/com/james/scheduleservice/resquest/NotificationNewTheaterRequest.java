package com.james.scheduleservice.resquest;

import com.james.scheduleservice.dto.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotificationNewTheaterRequest {
  private Long theaterId;
  private String theaterName;
  private String description;
  private LocationDTO locationDTO;
  private String firstImage;
}
