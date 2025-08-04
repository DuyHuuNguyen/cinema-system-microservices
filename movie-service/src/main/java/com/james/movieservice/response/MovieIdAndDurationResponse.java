package com.james.movieservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieIdAndDurationResponse {
  private Long id;
  private String name;
  private String firstImage;
  private Long duration;
}
