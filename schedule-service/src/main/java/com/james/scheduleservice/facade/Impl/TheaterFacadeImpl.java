package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.facade.TheaterFacade;
import com.james.scheduleservice.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterFacadeImpl implements TheaterFacade {
  private final TheaterService theaterService;
}
