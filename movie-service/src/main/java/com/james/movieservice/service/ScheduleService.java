package com.james.movieservice.service;

import com.james.movieservice.resquest.ValidAdminTheaterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "schedule-service")
public interface ScheduleService {
  @PostMapping(
      value = "/api/schedules/internal/verify-admin-theater",
      headers = "secret-key=movie-service")
  boolean validAdminTheater(@RequestBody ValidAdminTheaterRequest request);
}
