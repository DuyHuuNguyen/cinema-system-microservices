package com.james.scheduleservice.controller;

import com.james.scheduleservice.dto.FoodDTO;
import com.james.scheduleservice.facade.FingerFoodFacade;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/foods")
@RestController
@RequiredArgsConstructor
public class FingerFoodController {
  private final FingerFoodFacade fingerFoodFacade;

  @Hidden
  @GetMapping(value = "/internal/{id}", headers = "secret-key=booking-service")
  public FoodDTO findFingerFoodById(@PathVariable Long id) {
    return this.fingerFoodFacade.findFingerFoodById(id);
  }
}
