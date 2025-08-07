package com.james.bookingservice.service;

import com.james.bookingservice.dto.ScheduleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("schedule-service")
public interface ScheduleService {

  @GetMapping(value = "/api/v1/schedules/internal/{id}", headers = "secret-key=user-service001")
  ScheduleDTO findScheduleById(@PathVariable Long id);

  @GetMapping(
      value = "/api/v1/schedules/internal/scheduleId",
      headers = "secret-key=user-service001")
  Long findScheduleByCode(@RequestParam String scheduleCode);
}
