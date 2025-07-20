package com.james.movieservice.service;

import com.james.movieservice.entity.MovieRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MovieRateService {
  Page<MovieRate> findAll(Specification<MovieRate> specification, Pageable pageable);
}
