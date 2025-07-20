package com.james.movieservice.specification;

import com.james.movieservice.entity.MovieRate;
import org.springframework.data.jpa.domain.Specification;

public class MovieRateSpecification {

  public static Specification<MovieRate> hasMovieId(Long movieId) {
    return (root, query, cb) -> {
      if (movieId == null) return cb.conjunction();
      return cb.equal(root.get("movie").get("id"), movieId);
    };
  }
}
