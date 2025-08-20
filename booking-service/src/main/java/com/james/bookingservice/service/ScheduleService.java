package com.james.bookingservice.service;

import com.james.bookingservice.dto.FoodDTO;
import com.james.bookingservice.dto.ScheduleDTO;
import com.james.bookingservice.resquest.ValidAdminTheaterRequest;
import com.james.bookingservice.resquest.ValidScheduleOfTheaterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("schedule-service")
public interface ScheduleService {

  @GetMapping(value = "/api/v1/schedules/internal/{id}", headers = "secret-key=user-service001")
  ScheduleDTO findScheduleById(@PathVariable Long id);

  @GetMapping(
      value = "/api/v1/schedules/internal/scheduleId",
      headers = "secret-key=user-service001")
  Long findScheduleByCode(@RequestParam String scheduleCode);

  @PostMapping(
      value = "/api/v1/theaters/internal/food/{id}",
      headers = "secret-key=booking-service")
  Float getPriceOfFoodById(@PathVariable long id, @RequestParam("foodId") Long foodId);

  @GetMapping(value = "/api/v1/foods/internal/{id}", headers = "secret-key=booking-service")
  FoodDTO findFoodById(@PathVariable Long id);

  @PostMapping(
      value = "/api/v1/theaters/internal/verify-admin-theater",
      headers = "secret-key=movie-service")
  Boolean validAdminTheater(@RequestBody ValidAdminTheaterRequest request);

  @PostMapping(
      value = "/api/v1/schedules/internal/verify-schedule",
      headers = "secret-key=booking-service")
  Boolean validScheduleOfTheater(@RequestBody ValidScheduleOfTheaterRequest request);
}
