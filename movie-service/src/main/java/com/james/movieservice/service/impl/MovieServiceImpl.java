package com.james.movieservice.service.impl;

import com.james.movieservice.entity.Movie;
import com.james.movieservice.repository.MovieRepository;
import com.james.movieservice.service.MovieService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;

  @Override
  public void save(Movie movie) {
    this.movieRepository.save(movie);
  }

  @Override
  public Optional<Movie> findById(Long id) {
    return this.movieRepository.findById(id);
  }

  @Override
  public Page<Movie> findAll(Specification<Movie> specification, Pageable pageable) {
    return this.movieRepository.findAll(specification, pageable);
  }

  @Override
  public Optional<Movie> findMovieByTheaterIdAndMovieId(Long theaterId, Long id) {
    return this.movieRepository.findByTheaterIdAndMovieId(theaterId, id);
  }
}
