package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.response.*;
import com.james.scheduleservice.resquest.*;

public interface TheaterFacade {
  TheaterDTO findTheaterById(Long id);

  Boolean validAdminTheater(ValidAdminTheaterRequest request);

  void createTheater(UpsertTheaterRequest request);

  void addFingerFood(AddFingerFoodRequest request);

  void updateTheater(UpsertTheaterRequest request);

  BaseResponse<TheaterDetailResponse> findDetailTheaterById(Long id);

  BaseResponse<PaginationResponse<TheaterResponse>> findByFilter(TheaterCriteria criteria);

  BaseResponse<PaginationResponse<RoomResponse>> findRoomByFilter(RoomCriteria criteria);

  Float getPriceOfFood(long id, Long foodId);
}
