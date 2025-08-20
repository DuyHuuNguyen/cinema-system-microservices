package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.dto.FoodDTO;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.facade.FingerFoodFacade;
import com.james.scheduleservice.service.FingerFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FingerFoodFacadeImpl implements FingerFoodFacade {
  private final FingerFoodService fingerFoodService;

  @Override
  public FoodDTO findFingerFoodById(Long id) {
    var fingerFood =
        this.fingerFoodService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.FOOD_NOT_FOUND));
    return FoodDTO.builder()
        .foodType(fingerFood.getFoodType())
        .foodName(fingerFood.getFoodName())
        .price(fingerFood.getPrice())
        .id(fingerFood.getId())
        .build();
  }
}
