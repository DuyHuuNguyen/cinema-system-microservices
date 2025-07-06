package com.james.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TheaterDTO {
  private Long id;
  private String theaterName;
  private String description;
  private LocationDTO locationDTO;
}
