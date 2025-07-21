package com.james.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UpdateMovieDTO {
  private String title;
  private String description;
  private String duration;
  private String language;
  private Long releasedAt;
  private String poster;
  private String trailer;
  private String movie;
}
