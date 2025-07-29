package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.MovieSchedule;
import java.util.List;
import java.util.Optional;

public interface MovieScheduleService {
  Optional<MovieSchedule> findById(Long id);

  void save(MovieSchedule movieSchedule);

  List<MovieSchedule> findMovieSchedulesByDateAndTheaterIdAndRoomId(
      Long createdAt, Long roomId, Long theaterId);
}
