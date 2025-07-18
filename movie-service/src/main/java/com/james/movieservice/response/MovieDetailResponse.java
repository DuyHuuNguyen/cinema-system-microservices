package com.james.movieservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailResponse {
  private String title;
  private String description;
  private String duration;
  private String language;
  private Long releasedAt;
  private String poster;
  private String trailer;
  private String movie;
  private Long categoryId;
  private String categoryName;
  private Long theaterId;
  private String theaterName;
  private Integer starNumber;
}
