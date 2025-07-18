package com.james.movieservice.facade;

import com.james.movieservice.response.BaseResponse;
import com.james.movieservice.response.MovieDetailResponse;
import com.james.movieservice.resquest.UpsertMovieRequest;

public interface MovieFacade {
  void addMovie(UpsertMovieRequest request);

  BaseResponse<MovieDetailResponse> getMovieDetailById(Long id);
}
