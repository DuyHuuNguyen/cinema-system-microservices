package com.james.movieservice.facade.impl;

import com.james.movieservice.config.SecurityUserDetails;
import com.james.movieservice.entity.Movie;
import com.james.movieservice.enums.ErrorCode;
import com.james.movieservice.exception.EntityNotFoundException;
import com.james.movieservice.exception.PermissionDeniedException;
import com.james.movieservice.facade.MovieFacade;
import com.james.movieservice.response.BaseResponse;
import com.james.movieservice.response.MovieDetailResponse;
import com.james.movieservice.resquest.UpsertMovieRequest;
import com.james.movieservice.resquest.ValidAdminTheaterRequest;
import com.james.movieservice.service.CategoryService;
import com.james.movieservice.service.MovieService;
import com.james.movieservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieFacadeImpl implements MovieFacade {
  private final MovieService movieService;
  private final ScheduleService scheduleService;
  private final CategoryService categoryService;

  @Override
  @Transactional
  public void addMovie(UpsertMovieRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var validAdminTheaterRequest =
        ValidAdminTheaterRequest.builder()
            .adminId(principal.getId())
            .theaterId(request.getTheaterId())
            .build();
    var isValidAdmin = this.scheduleService.validAdminTheater(validAdminTheaterRequest);

    if (!isValidAdmin) throw new PermissionDeniedException(ErrorCode.NOT_ADMIN_THEATER);

    var category =
        this.categoryService
            .findById(request.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));

    var movie =
        Movie.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .duration(request.getDuration())
            .language(request.getLanguage())
            .releasedAt(request.getReleasedAt())
            .poster(request.getPoster())
            .trailer(request.getTrailer())
            .movie(request.getMovie())
            .theaterId(request.getTheaterId())
            .build();
    movie.addCategory(category);
    this.movieService.save(movie);
  }

  @Override
  public BaseResponse<MovieDetailResponse> getMovieDetailById(Long id) {
    var movie =
        this.movieService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND));

    var theater = this.scheduleService.findById(movie.getTheaterId());

    var isNotFoundTheater = theater == null;
    if (isNotFoundTheater) throw new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND);

    var movieDetailResponse =
        MovieDetailResponse.builder()
            .title(movie.getTitle())
            .duration(movie.getDuration())
            .description(movie.getDescription())
            .language(movie.getLanguage())
            .releasedAt(movie.getReleasedAt())
            .poster(movie.getPoster())
            .trailer(movie.getTrailer())
            .movie(movie.getMovie())
            .categoryId(movie.getCategoryId())
            .categoryName(movie.getCategoryName())
            .theaterId(theater.getId())
            .theaterName(theater.getName())
            .build();

    return BaseResponse.build(movieDetailResponse, true);
  }
}
