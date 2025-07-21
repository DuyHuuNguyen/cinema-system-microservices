package com.james.movieservice.service;

import com.james.movieservice.entity.MovieRate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MovieRateService {
  Optional<MovieRate> findById(Long id);

  void save(MovieRate movieRate);

  Double getAverageRatingByMovieId(Long movieId);

  Page<MovieRate> findAll(Specification<MovieRate> specification, Pageable pageable);
}
