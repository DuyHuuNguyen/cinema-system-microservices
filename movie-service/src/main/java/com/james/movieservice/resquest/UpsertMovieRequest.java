package com.james.movieservice.resquest;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpsertMovieRequest {
  @Hidden private Long id;
  private String title;
  private String description;
  private Long duration;
  private String language;
  private Long releasedAt;
  private String poster;
  private String trailer;
  private String movie;
  private Long theaterId;
  private Long categoryId;

  public void attachMovieId(long id) {
    this.id = id;
  }
}
