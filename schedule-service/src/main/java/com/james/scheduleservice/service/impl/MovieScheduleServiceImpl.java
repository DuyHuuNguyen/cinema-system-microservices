package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.repository.MovieScheduleRepository;
import com.james.scheduleservice.service.MovieScheduleService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
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

  @Override
  public List<MovieSchedule> findMovieSchedulesByDateAndTheaterIdAndRoomId(
      Long createdAt, Long roomId, Long theaterId) {
    return this.movieScheduleRepository.findMovieSchedulesByDateAndTheaterIdAndRoomId(
        createdAt, roomId, theaterId);
  }

  @Override
  public void remove(MovieSchedule movieSchedule) {
    this.movieScheduleRepository.delete(movieSchedule);
  }

  @Override
  public Page<MovieSchedule> findAll(
      Specification<MovieSchedule> specification, Pageable pageable) {
    return this.movieScheduleRepository.findAll(specification, pageable);
  }

  @Override
  public Optional<MovieSchedule> findByCode(String scheduleCode) {
    return this.movieScheduleRepository.findByScheduleCode(scheduleCode);
  }
}
