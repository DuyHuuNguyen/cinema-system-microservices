package com.james.movieservice.service;

import com.james.movieservice.entity.Movie;
import java.util.Optional;

public interface MovieService {
  void save(Movie movie);

  Optional<Movie> findById(Long id);
}
