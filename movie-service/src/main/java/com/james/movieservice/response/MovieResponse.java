package com.james.movieservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieResponse {
  private Long id;
  private String title;
  private Long duration;
  private String language;
  private String poster;
  private Long theaterId;
}
