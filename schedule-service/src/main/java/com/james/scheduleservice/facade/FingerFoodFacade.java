package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.FoodDTO;

public interface FingerFoodFacade {
  FoodDTO findFingerFoodById(Long id);
}
