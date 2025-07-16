package com.james.movieservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpsertMovieRequest {
  private String title;
  private String description;
  private String duration;
  private String language;
  private Long releasedAt;
  private String poster;
  private String trailer;
  private String movie;
  private Long theaterId;
  private Long categoryId;
}
