package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.MovieSchedule;
import java.util.Optional;

public interface MovieScheduleService {
  Optional<MovieSchedule> findById(Long id);

  void save(MovieSchedule movieSchedule);
}
