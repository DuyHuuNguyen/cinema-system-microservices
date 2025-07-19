package com.james.movieservice.facade;

import com.james.movieservice.response.BaseResponse;
import com.james.movieservice.response.MovieDetailResponse;
import com.james.movieservice.response.MovieResponse;
import com.james.movieservice.response.PaginationResponse;
import com.james.movieservice.resquest.MovieCriteria;
import com.james.movieservice.resquest.UpsertMovieRequest;

public interface MovieFacade {
  void addMovie(UpsertMovieRequest request);

  BaseResponse<MovieDetailResponse> getMovieDetailById(Long id);

  BaseResponse<PaginationResponse<MovieResponse>> getByFilter(MovieCriteria criteria);

  void updateMovie(UpsertMovieRequest request);
}
