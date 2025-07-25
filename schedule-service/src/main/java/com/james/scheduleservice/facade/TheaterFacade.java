package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.resquest.AddFingerFoodRequest;
import com.james.scheduleservice.resquest.UpsertTheaterRequest;
import com.james.scheduleservice.resquest.ValidAdminTheaterRequest;

public interface TheaterFacade {
  TheaterDTO findTheaterById(Long id);

  Boolean validAdminTheater(ValidAdminTheaterRequest request);

  void createTheater(UpsertTheaterRequest request);

  void addFingerFood(AddFingerFoodRequest request);

  void updateTheater(UpsertTheaterRequest request);
}
