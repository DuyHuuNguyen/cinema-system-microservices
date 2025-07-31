package com.james.userservice.service;

import com.james.userservice.dto.ScheduleDTO;
import com.james.userservice.response.TheaterProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "schedule-service")
public interface ScheduleService {
  @GetMapping(value = "/api/v1/schedules/internal/{id}", headers = "secret-key=user-service001")
  ScheduleDTO findScheduleById(@PathVariable Long id);

  @GetMapping(value = "/api/v1/theaters/internal/{id}", headers = "secret-key=user-service001")
  TheaterProfileResponse findTheaterById(@PathVariable Long id);
}
