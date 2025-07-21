package com.james.movieservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterResponse {
  private Long id;
  private String name;
  private String description;
}
