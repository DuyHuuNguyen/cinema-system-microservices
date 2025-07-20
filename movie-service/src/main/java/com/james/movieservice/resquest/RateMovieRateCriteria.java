package com.james.movieservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateMovieRateCriteria extends BaseCriteria {
  private Long movieId;
}
