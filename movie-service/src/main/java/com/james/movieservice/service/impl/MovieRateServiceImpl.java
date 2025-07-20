package com.james.movieservice.service.impl;

import com.james.movieservice.entity.MovieRate;
import com.james.movieservice.repository.MovieRateRepository;
import com.james.movieservice.service.MovieRateService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieRateServiceImpl implements MovieRateService {
  private final MovieRateRepository movieRateRepository;

  @Override
  public Optional<MovieRate> findById(Long id) {
    return this.movieRateRepository.findById(id);
  }

  @Override
  public void save(MovieRate movieRate) {
    this.movieRateRepository.save(movieRate);
  }

  @Override
  public Page<MovieRate> findAll(Specification<MovieRate> specification, Pageable pageable) {
    return movieRateRepository.findAll(specification, pageable);
  }
}
