package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.config.SecurityUserDetails;
import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.exception.PermissionDeniedException;
import com.james.scheduleservice.facade.MovieScheduleFacade;
import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.response.DoScheduleResponse;
import com.james.scheduleservice.resquest.DoScheduleRequest;
import com.james.scheduleservice.service.MovieScheduleService;
import com.james.scheduleservice.service.MovieService;
import com.james.scheduleservice.service.TheaterService;
import com.james.scheduleservice.until.MovieUtil;
import com.james.scheduleservice.until.TimeConverter;
import com.james.scheduleservice.until.TimeLineUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    rooms.forEach(timeLine::addRoom);

    var allocateScreeningDTOS = timeLine.buildAllocateScreeningDTOS();
    log.info("{}", allocateScreeningDTOS.toString());
    var isDemoSchedule = request.getIsDemoSchedule();

    if (!isDemoSchedule) {
      saveMovieSchedule(timeLine.getMovieSchedules());
    }

    return BaseResponse.build(
        DoScheduleResponse.builder().allocateScreeningDTOS(allocateScreeningDTOS).build(), true);
  }

  private void saveMovieSchedule(List<MovieSchedule> movieSchedules) {
    movieSchedules.stream().forEach(movieScheduleService::save);
  }
}
