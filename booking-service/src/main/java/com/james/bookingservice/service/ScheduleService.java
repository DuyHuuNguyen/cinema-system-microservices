package com.james.bookingservice.service;

import com.james.bookingservice.dto.ScheduleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("schedule-service")
public interface ScheduleService {

  @GetMapping(value = "/api/v1/schedules/internal/{id}", headers = "secret-key=booking-service001")
  ScheduleDTO findScheduleById(@PathVariable Long id);
}
