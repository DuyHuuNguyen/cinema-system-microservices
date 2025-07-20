package com.james.movieservice.controller;

import com.james.movieservice.facade.MovieFacade;
import com.james.movieservice.response.*;
import com.james.movieservice.resquest.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/movies")
@RestController
@RequiredArgsConstructor
public class MovieController {
  private final MovieFacade movieFacade;

  @PostMapping
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> addMovie(@RequestBody UpsertMovieRequest request) {
    this.movieFacade.addMovie(request);
    return BaseResponse.ok();
  }

  @PutMapping("/{id}")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> updateMovie(
      @PathVariable Long id, @RequestBody UpsertMovieRequest request) {
    request.attachMovieId(id);
    this.movieFacade.updateMovie(request);
    return BaseResponse.ok();
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<MovieDetailResponse> getMovieDetailById(@PathVariable Long id) {
    return this.movieFacade.getMovieDetailById(id);
  }

  @GetMapping()
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<MovieResponse>> getByFilter(
      @NonNull MovieCriteria criteria) {
    return this.movieFacade.getByFilter(criteria);
  }

  @PostMapping("/rate/{id}")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<Void> rateMovie(
      @PathVariable Long id, @RequestBody RateMovieRequest request) {
    request.attachMovieId(id);
    this.movieFacade.rateMovie(request);
    return BaseResponse.ok();
  }

  @GetMapping("/rates")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<RateResponse>> getRate(
      @Nullable RateMovieRateCriteria criteria) {
    return movieFacade.getRateMovies(criteria);
  }
}
