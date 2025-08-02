package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.MovieSchedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MovieScheduleService {
  Optional<MovieSchedule> findById(Long id);

  void save(MovieSchedule movieSchedule);

  List<MovieSchedule> findMovieSchedulesByDateAndTheaterIdAndRoomId(
      Long createdAt, Long roomId, Long theaterId);

  void remove(MovieSchedule movieSchedule);

  Page<MovieSchedule> findAll(Specification<MovieSchedule> specification, Pageable pageable);
}
