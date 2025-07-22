package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.Theater;
import com.james.scheduleservice.repository.TheaterRepository;
import com.james.scheduleservice.service.TheaterService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}
