package com.james.movieservice.facade.impl;

import com.james.movieservice.facade.MovieFacade;
import com.james.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieFacadeImpl implements MovieFacade {
  private final MovieService movieService;
}
