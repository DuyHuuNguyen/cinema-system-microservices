package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.facade.TheaterFacade;
import com.james.scheduleservice.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterFacadeImpl implements TheaterFacade {
  private final TheaterService theaterService;

  @Override
  public TheaterDTO findTheaterById(Long id) {
    var theater =
        this.theaterService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND));
    return TheaterDTO.builder().id(theater.getId()).theaterName(theater.getTheaterName()).build();
  }
}
