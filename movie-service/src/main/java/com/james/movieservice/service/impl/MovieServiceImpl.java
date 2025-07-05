package com.james.movieservice.service.impl;

import com.james.movieservice.repository.MovieRepository;
import com.james.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;
}
