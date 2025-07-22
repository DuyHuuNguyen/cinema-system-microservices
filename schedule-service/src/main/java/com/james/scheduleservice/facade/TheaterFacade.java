package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.resquest.ValidAdminTheaterRequest;

public interface TheaterFacade {
  TheaterDTO findTheaterById(Long id);

  Boolean validAdminTheater(ValidAdminTheaterRequest request);
}
