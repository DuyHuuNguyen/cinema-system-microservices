package com.james.scheduleservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterResponse {
  private Long id;
  private String name;
  private String description;
  private String firstImage;
}
