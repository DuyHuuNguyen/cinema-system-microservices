package com.james.movieservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieCriteria extends BaseCriteria {
  private String title;
  private String description;
  private String category;
  private String duration;
  private Long releaseAt;
  private Long theaterId;
}
