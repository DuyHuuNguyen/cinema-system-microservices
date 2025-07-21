package com.james.movieservice.service;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserService {
  @GetMapping(value = "/api/v1/users/hobbies/{id}", headers = "secret-key=movie-service")
  List<String> getAllHobbies(@PathVariable Long id);
}
