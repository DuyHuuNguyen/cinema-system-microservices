package com.james.userservice.service;

import com.james.userservice.dto.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service")
public interface MovieService {
  @GetMapping("/api/v1/movies/{id}")
  MovieDTO findMovieById(@PathVariable Long id);
}
