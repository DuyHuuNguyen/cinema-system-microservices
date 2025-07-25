package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.Theater;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface TheaterService {
  Optional<Theater> findById(Long id);

  void save(Theater theater);

  Theater saveAndFlush(Theater theater);

  Optional<Theater> findTheaterByDirectorIdAndTheaterId(Long directorId, Long theaterId);

  Page<Theater> findAll(Specification<Theater> specification, Pageable pageable);
}
