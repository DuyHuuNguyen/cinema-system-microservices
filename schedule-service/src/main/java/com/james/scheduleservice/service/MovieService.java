package com.james.scheduleservice.service;

import com.james.scheduleservice.dto.MovieDTO;
import com.james.scheduleservice.until.MovieUtil;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "movie-service")
public interface MovieService {
  @GetMapping(value = "/api/v1/movies/internal", headers = "secret-key=schedule-service")
  List<MovieUtil> findMovieIdByIds(@RequestParam Long theaterId, @RequestParam List<Long> movieIds);

  @GetMapping(value = "/api/v1/movies/internal/{id}", headers = "secret-key=schedule-service")
  MovieDTO findMovieById(@PathVariable Long id);
}
