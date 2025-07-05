package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.repository.TheaterRepository;
import com.james.scheduleservice.service.TheaterRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterRateServiceImpl implements TheaterRateService {
  private final TheaterRepository theaterRepository;
}
