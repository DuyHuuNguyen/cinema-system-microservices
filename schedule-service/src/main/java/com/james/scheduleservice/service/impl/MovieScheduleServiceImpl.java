package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.repository.MovieScheduleRepository;
import com.james.scheduleservice.service.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieScheduleServiceImpl implements MovieScheduleService {
  private final MovieScheduleRepository movieScheduleRepository;
}
