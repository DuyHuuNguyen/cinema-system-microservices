package com.james.movieservice.controller;

import com.james.movieservice.facade.MovieFacade;
import com.james.movieservice.response.BaseResponse;
import com.james.movieservice.response.MovieDetailResponse;
import com.james.movieservice.resquest.UpsertMovieRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

  @GetMapping("/{id}")
  public BaseResponse<MovieDetailResponse> getMovieDetailById(@PathVariable Long id) {
    return this.movieFacade.getMovieDetailById(id);
  }
}
