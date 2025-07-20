package com.james.movieservice.facade.impl;

import com.james.movieservice.config.SecurityUserDetails;
import com.james.movieservice.dto.UpdateMovieDTO;
import com.james.movieservice.entity.Movie;
import com.james.movieservice.entity.MovieRate;
import com.james.movieservice.entity.MovieRateAsset;
import com.james.movieservice.enums.ErrorCode;
import com.james.movieservice.exception.EntityNotFoundException;
import com.james.movieservice.exception.PermissionDeniedException;
import com.james.movieservice.facade.MovieFacade;
import com.james.movieservice.response.*;
import com.james.movieservice.resquest.*;
import com.james.movieservice.service.CategoryService;
import com.james.movieservice.service.MovieRateService;
import com.james.movieservice.service.MovieService;
import com.james.movieservice.service.ScheduleService;
import com.james.movieservice.specification.MovieRateSpecification;
import com.james.movieservice.specification.MovieSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
  private final MovieRateService movieRateService;

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

  @Override
  public BaseResponse<PaginationResponse<MovieResponse>> getByFilter(MovieCriteria criteria) {
    Specification<Movie> specification =
        MovieSpecification.hasTitle(criteria.getTitle())
            .and(MovieSpecification.hasDescription(criteria.getDescription()))
            .and(MovieSpecification.hasCategory(criteria.getCategory()))
            .and(MovieSpecification.hasDuration(criteria.getDuration()))
            .and(MovieSpecification.hasReleaseAt(criteria.getReleaseAt()))
            .and(MovieSpecification.hasTheaterId(criteria.getTheaterId()));
    Pageable pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize());
    var moviePage = this.movieService.findAll(specification, pageable);

    var paginationResponse =
        PaginationResponse.<MovieResponse>builder()
            .data(
                moviePage
                    .get()
                    .map(
                        movie ->
                            MovieResponse.builder()
                                .id(movie.getId())
                                .title(movie.getTitle())
                                .duration(movie.getDuration())
                                .language(movie.getLanguage())
                                .poster(movie.getPoster())
                                .movie(movie.getMovie())
                                .theaterId(movie.getTheaterId())
                                .build())
                    .toList())
            .currentPage(criteria.getCurrentPage())
            .totalElements(moviePage.getNumberOfElements())
            .totalPages(moviePage.getTotalPages())
            .build();

    return BaseResponse.build(paginationResponse, true);
  }

  @Override
  public void updateMovie(UpsertMovieRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    var validAdminTheaterRequest =
        ValidAdminTheaterRequest.builder()
            .adminId(principal.getId())
            .theaterId(request.getTheaterId())
            .build();
    var isValidAdmin = this.scheduleService.validAdminTheater(validAdminTheaterRequest);

    if (!isValidAdmin) throw new PermissionDeniedException(ErrorCode.NOT_ADMIN_THEATER);

    var movie =
        this.movieService
            .findMovieByTheaterIdAndMovieId(request.getTheaterId(), request.getId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND));
    var updateMovieDTO =
        UpdateMovieDTO.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .duration(request.getDuration())
            .language(request.getLanguage())
            .releasedAt(request.getReleasedAt())
            .poster(request.getPoster())
            .trailer(request.getTrailer())
            .movie(request.getMovie())
            .build();
    movie.updateInfo(updateMovieDTO);
    this.movieService.save(movie);
  }

  @Override
  public void rateMovie(RateMovieRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var movie =
        this.movieService
            .findById(request.getMovieId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND));
    var rate =
        MovieRate.builder()
            .movie(movie)
            .starNumber(request.getNumberOfStars())
            .comment(request.getComment())
            .ownerId(principal.getId())
            .build();

    request.getRateAssetDTOS().stream()
        .map(
            rateAssetDTO ->
                MovieRateAsset.builder()
                    .mediaType(rateAssetDTO.getMediaType())
                    .mediaKey(rateAssetDTO.getMediaKey())
                    .movieRate(rate)
                    .build())
        .forEach(movieRateAsset -> rate.addAsset(movieRateAsset));
    movie.addRate(rate);
  }

  @Override
  public BaseResponse<PaginationResponse<RateResponse>> getRateMovies(
      RateMovieRateCriteria criteria) {
    var specification = MovieRateSpecification.hasMovieId(criteria.getMovieId());
    var pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize());

    var movieRatePage = this.movieRateService.findAll(specification, pageable); // bug
    var paginationRate =
        PaginationResponse.<RateResponse>builder()
            .data(
                movieRatePage
                    .get()
                    .map(
                        movieRate ->
                            RateResponse.builder()
                                .comment(movieRate.getComment())
                                .numberOfStar(movieRate.getStarNumber())
                                .ownerId(movieRate.getOwnerId())
                                .rateAssetDTOS(
                                    movieRate.getMovieRateAssets().stream()
                                        .map(MovieRateAsset::getRateAssetDTO)
                                        .toList())
                                .build())
                    .toList())
            .totalElements(movieRatePage.getNumberOfElements())
            .totalPages(movieRatePage.getTotalPages())
            .currentPage(criteria.getCurrentPage())
            .build();
    return BaseResponse.build(paginationRate, true);
  }
}
