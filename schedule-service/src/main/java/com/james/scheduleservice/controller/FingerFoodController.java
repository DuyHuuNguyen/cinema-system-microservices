package com.james.scheduleservice.controller;

import com.james.scheduleservice.dto.FoodDTO;
import com.james.scheduleservice.facade.FingerFoodFacade;
import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.response.FoodResponse;
import com.james.scheduleservice.response.PaginationResponse;
import com.james.scheduleservice.resquest.FoodCriteria;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/foods")
@RestController
@RequiredArgsConstructor
public class FingerFoodController {
  private final FingerFoodFacade fingerFoodFacade;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Food APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<PaginationResponse<FoodResponse>> findAllFood(
      @Nonnull FoodCriteria criteria) {
    return this.fingerFoodFacade.findAllFood(criteria);
  }

  @Hidden
  @GetMapping(value = "/internal/{id}", headers = "secret-key=booking-service")
  public FoodDTO findFingerFoodById(@PathVariable Long id) {
    return this.fingerFoodFacade.findFingerFoodById(id);
  }
}
