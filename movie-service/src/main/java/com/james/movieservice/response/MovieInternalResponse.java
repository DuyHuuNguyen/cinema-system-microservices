package com.james.movieservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieInternalResponse {
  private String title;
  private String duration;
  private String language;
  private String poster;
  private Integer star;
}
