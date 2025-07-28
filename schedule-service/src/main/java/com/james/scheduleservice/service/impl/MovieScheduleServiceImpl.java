package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.repository.MovieScheduleRepository;
import com.james.scheduleservice.service.MovieScheduleService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieScheduleServiceImpl implements MovieScheduleService {
  private final MovieScheduleRepository movieScheduleRepository;

  @Override
  public Optional<MovieSchedule> findById(Long id) {
    return movieScheduleRepository.findById(id);
  }

  @Override
  public void save(MovieSchedule movieSchedule) {
    this.movieScheduleRepository.save(movieSchedule);
  }
}
