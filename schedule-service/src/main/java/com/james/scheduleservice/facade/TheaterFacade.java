package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.response.PaginationResponse;
import com.james.scheduleservice.response.TheaterDetailResponse;
import com.james.scheduleservice.response.TheaterResponse;
import com.james.scheduleservice.resquest.AddFingerFoodRequest;
import com.james.scheduleservice.resquest.TheaterCriteria;
import com.james.scheduleservice.resquest.UpsertTheaterRequest;
import com.james.scheduleservice.resquest.ValidAdminTheaterRequest;

public interface TheaterFacade {
  TheaterDTO findTheaterById(Long id);

  Boolean validAdminTheater(ValidAdminTheaterRequest request);

  void createTheater(UpsertTheaterRequest request);

  void addFingerFood(AddFingerFoodRequest request);

  void updateTheater(UpsertTheaterRequest request);

  BaseResponse<TheaterDetailResponse> findDetailTheaterById(Long id);

  BaseResponse<PaginationResponse<TheaterResponse>> findByFilter(TheaterCriteria criteria);
}
