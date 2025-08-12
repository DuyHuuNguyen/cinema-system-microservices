package com.james.bookingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "theater-service")
public interface TheaterService {
  @PostMapping(value = "/api/theaters/internal/food/{}", headers = "booking-service")
  Float getPriceOfFoodById(@PathVariable long id, @RequestParam Long theaterId);
}
