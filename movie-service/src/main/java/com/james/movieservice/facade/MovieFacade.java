package com.james.movieservice.facade;

import com.james.movieservice.resquest.UpsertMovieRequest;

public interface MovieFacade {
  void addMovie(UpsertMovieRequest request);
}
