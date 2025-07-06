package com.james.userservice.service;

import com.james.userservice.dto.ScheduleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "schedule-service")
public interface ScheduleService {
  @GetMapping("/api/v1/schedules/{id}")
  ScheduleDTO findScheduleById(@PathVariable Long id);
}
