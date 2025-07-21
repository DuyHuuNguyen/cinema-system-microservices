package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.facade.MovieScheduleFacade;
import com.james.scheduleservice.service.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieScheduleFacadeImpl implements MovieScheduleFacade {
  private final MovieScheduleService movieScheduleService;

  @Override
  public ScheduleDTO findScheduleById(Long id) {
    var schedule =
        this.movieScheduleService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND));
    return ScheduleDTO.builder()
        .scheduleId(schedule.getId())
        .startedAt(schedule.getStartedAt())
        .finishedAt(schedule.getFinishedAt())
        .theaterName(schedule.getTheaterName())
        .locationTheater(schedule.getLocationTheater())
        .build();
  }
}
