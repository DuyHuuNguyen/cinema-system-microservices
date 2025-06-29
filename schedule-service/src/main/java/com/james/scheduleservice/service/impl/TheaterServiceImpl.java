package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.repository.TheaterRepository;
import com.james.scheduleservice.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {
  private final TheaterRepository theaterRepository;
}
