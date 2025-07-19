package com.james.movieservice.service;

import com.james.movieservice.entity.Movie;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MovieService {
  void save(Movie movie);

  Optional<Movie> findById(Long id);

  Page<Movie> findAll(Specification<Movie> specification, Pageable pageable);

  Optional<Movie> findMovieByTheaterIdAndMovieId(Long theaterId, Long id);
}
