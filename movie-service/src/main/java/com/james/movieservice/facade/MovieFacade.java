package com.james.movieservice.facade;

import com.james.movieservice.response.*;
import com.james.movieservice.resquest.*;

public interface MovieFacade {
  void addMovie(UpsertMovieRequest request);

  BaseResponse<MovieDetailResponse> getMovieDetailById(Long id);

  BaseResponse<PaginationResponse<MovieResponse>> getByFilter(MovieCriteria criteria);

  void updateMovie(UpsertMovieRequest request);

  void rateMovie(RateMovieRequest request);

  BaseResponse<PaginationResponse<RateResponse>> getRateMovies(RateMovieRateCriteria criteria);
}
