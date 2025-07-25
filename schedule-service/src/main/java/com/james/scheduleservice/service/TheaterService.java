package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.Theater;
import java.util.Optional;

public interface TheaterService {
  Optional<Theater> findById(Long id);

  void save(Theater theater);

  Optional<Theater> findTheaterByDirectorIdAndTheaterId(Long directorId, Long theaterId);
}
