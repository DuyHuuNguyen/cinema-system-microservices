package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.facade.MovieScheduleFacade;
import com.james.scheduleservice.service.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieScheduleFacadeImpl implements MovieScheduleFacade {
  private final MovieScheduleService movieScheduleService;
}
