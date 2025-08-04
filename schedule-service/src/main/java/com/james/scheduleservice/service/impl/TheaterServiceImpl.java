package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.Theater;
import com.james.scheduleservice.repository.TheaterRepository;
import com.james.scheduleservice.service.TheaterService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {
  private final TheaterRepository theaterRepository;

  @Override
  public Optional<Theater> findById(Long id) {
    return this.theaterRepository.findById(id);
  }

  @Override
  public void save(Theater theater) {
    this.theaterRepository.save(theater);
  }

  @Override
  public Theater saveAndFlush(Theater theater) {
    return this.theaterRepository.saveAndFlush(theater);
  }

  @Override
  public Optional<Theater> findTheaterByDirectorIdAndTheaterId(Long directorId, Long theaterId) {
    return this.theaterRepository.findTheaterByDirectorIdAndTheaterId(directorId, theaterId);
  }

  @Override
  public Page<Theater> findAll(Specification<Theater> specification, Pageable pageable) {
    return this.theaterRepository.findAll(specification, pageable);
  }
}
