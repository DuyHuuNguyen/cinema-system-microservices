package com.james.movieservice.facade;

import com.james.movieservice.response.*;
import com.james.movieservice.resquest.*;
import java.util.List;

public interface MovieFacade {
  void addMovie(UpsertMovieRequest request);

  BaseResponse<MovieDetailResponse> getMovieDetailById(Long id);

  BaseResponse<PaginationResponse<MovieResponse>> getByFilter(MovieCriteria criteria);

  void updateMovie(UpsertMovieRequest request);

  void rateMovie(RateMovieRequest request);

  BaseResponse<PaginationResponse<RateResponse>> getRateMovies(RateMovieRateCriteria criteria);

  void removeRate(Long id);

  void updateRateMovie(UpdateRateMovieRequest request);

  List<MovieIdAndDurationResponse> findMovieByIds(Long theaterId, List<Long> movieIds);
}
