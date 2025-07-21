package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.TheaterDTO;

public interface TheaterFacade {
  TheaterDTO findTheaterById(Long id);
}
