package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.FoodDTO;
import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.response.FoodResponse;
import com.james.scheduleservice.response.PaginationResponse;
import com.james.scheduleservice.resquest.FoodCriteria;

public interface FingerFoodFacade {
  FoodDTO findFingerFoodById(Long id);

  BaseResponse<PaginationResponse<FoodResponse>> findAllFood(FoodCriteria criteria);
}
