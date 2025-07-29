package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.config.SecurityUserDetails;
import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.enums.ScheduleKeyEnum;
import com.james.scheduleservice.exception.CreateScheduleInThePastException;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.exception.PermissionDeniedException;
import com.james.scheduleservice.exception.ScheduleIsDoneException;
import com.james.scheduleservice.facade.MovieScheduleFacade;
import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.response.DoScheduleResponse;
import com.james.scheduleservice.resquest.DoScheduleRequest;
import com.james.scheduleservice.service.CacheService;
import com.james.scheduleservice.service.MovieScheduleService;
import com.james.scheduleservice.service.MovieService;
import com.james.scheduleservice.service.TheaterService;
import com.james.scheduleservice.until.MovieUtil;
import com.james.scheduleservice.until.TimeConverter;
import com.james.scheduleservice.until.TimeLineUtil;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieScheduleFacadeImpl implements MovieScheduleFacade {
  private final MovieScheduleService movieScheduleService;
  private final TheaterService theaterService;
  private final MovieService movieService;
  private final CacheService cacheService;

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

  @Override
  @Transactional
  public BaseResponse<DoScheduleResponse> doSchedule(DoScheduleRequest request) {
    var isValidDayInThePast =
        LocalDate.now().isAfter(request.getCreatedAt())
            || LocalDate.now().isEqual(request.getCreatedAt());
    if (isValidDayInThePast)
      throw new CreateScheduleInThePastException(ErrorCode.NOT_CREATE_SCHEDULE_IN_THE_PAST);

    var cacheKeySchedule =
        String.format(
            ScheduleKeyEnum.SCHEDULE_KEY.getKey(),
            request.getTheaterId(),
            request.getCreatedAt().toString());

    var isDoneSchedule = cacheService.hasKey(cacheKeySchedule);
    if (isDoneSchedule) throw new ScheduleIsDoneException(ErrorCode.SCHEDULED);

    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var theater =
        this.theaterService
            .findById(request.getTheaterId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND));

    var isValidAdminTheater = theater.getDirectorId().equals(principal.getId());
    if (!isValidAdminTheater) throw new PermissionDeniedException(ErrorCode.NOT_ADMIN);

    var rooms =
        theater.getRooms().stream()
            .filter(
                room ->
                    request.getRoomIds().stream().anyMatch(roomId -> roomId.equals(room.getId())));

    var movieUtils = movieService.findMovieIdByIds(request.getTheaterId(), request.getMovieIds());

    var startedAt = TimeConverter.convertStringToDateTime(request.getStartedAt());
    var endedAt = TimeConverter.convertStringToDateTime(request.getEndAt());

    var timeLine = TimeLineUtil.build(startedAt, endedAt);

    Queue<MovieUtil> movies = new LinkedList<>();
    movies.addAll(movieUtils);
    timeLine.addMovies(movies);
    timeLine.addTheater(theater);
    timeLine.addMiddleSection(request.getMiddleSection());
    rooms.forEach(timeLine::addRoom);

    var allocateScreeningDTOS = timeLine.buildAllocateScreeningDTOS();
    log.info("{}", allocateScreeningDTOS.toString());

    var movieSchedules = timeLine.getMovieSchedules();
    var isDemoSchedule = request.getIsDemoSchedule();
    if (!isDemoSchedule) {
      saveMovieSchedule(movieSchedules);

      var cacheValueSchedule = UUID.randomUUID().toString();
      int timeout = request.getCreatedAt().compareTo(LocalDate.now()) + 2;
      cacheService.store(cacheKeySchedule, cacheValueSchedule, timeout, TimeUnit.DAYS);
    }

    return BaseResponse.build(
        DoScheduleResponse.builder().allocateScreeningDTOS(allocateScreeningDTOS).build(), true);
  }

  @Override
  @Transactional
  public void deleteScheduleById(Long id, Long theaterId) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var theater =
        this.theaterService
            .findById(theaterId)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND));
    var isValidAdminTheater = theater.getDirectorId().equals(principal.getId());
    if (!isValidAdminTheater) throw new PermissionDeniedException(ErrorCode.NOT_ADMIN);

    var movieSchedule =
        this.movieScheduleService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND));
    this.movieScheduleService.remove(movieSchedule);
  }

  private void saveMovieSchedule(List<MovieSchedule> movieSchedules) {
    movieSchedules.stream().forEach(movieScheduleService::save);
  }
}
