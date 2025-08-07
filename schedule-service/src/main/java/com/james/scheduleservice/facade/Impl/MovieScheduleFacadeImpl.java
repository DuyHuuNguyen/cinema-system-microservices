package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.config.SecurityUserDetails;
import com.james.scheduleservice.dto.MovieDTO;
import com.james.scheduleservice.dto.ProducerSaveTicketDTO;
import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.enums.ScheduleKeyEnum;
import com.james.scheduleservice.exception.*;
import com.james.scheduleservice.facade.MovieScheduleFacade;
import com.james.scheduleservice.response.*;
import com.james.scheduleservice.resquest.DoScheduleRequest;
import com.james.scheduleservice.resquest.ScheduleCriteria;
import com.james.scheduleservice.resquest.UpsertScheduleRequest;
import com.james.scheduleservice.service.*;
import com.james.scheduleservice.specification.MovieScheduleSpecification;
import com.james.scheduleservice.until.MovieUtil;
import com.james.scheduleservice.until.TimeConverter;
import com.james.scheduleservice.until.TimeLineUtil;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
  private final ProducerHandleScheduleService producerHandleScheduleService;
  private final ProducerHandleTicketService producerHandleTicketService;

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
  public BaseResponse<DoScheduleResponse> doSchedules(DoScheduleRequest request) {
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

    var startedAt = TimeConverter.convertStringToDateTime(request.getStartedAt());
    var endedAt = TimeConverter.convertStringToDateTime(request.getEndAt());

    var timeLine = TimeLineUtil.build(startedAt, endedAt);

    Queue<MovieUtil> movies = new LinkedList<>();

    try {
      var movieUtils = movieService.findMovieIdByIds(request.getTheaterId(), request.getMovieIds());
      movies.addAll(movieUtils);
    } catch (Exception e) {
      throw new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND);
    }
    timeLine.addMovies(movies);
    timeLine.addTheater(theater);
    timeLine.addMiddleSection(request.getMiddleSection());
    rooms.forEach(timeLine::addRoom);

    var allocateScreeningDTOS = timeLine.buildAllocateScreeningDTOS();
    log.info("{}", allocateScreeningDTOS.toString());

    var movieSchedules = timeLine.getMovieSchedules();
    var isDemoSchedule = request.getIsDemoSchedule();
    if (!isDemoSchedule) {

      //      this.producerHandleScheduleService.saveMovieSchedule(movieSchedules);
      movieSchedules.forEach(
          schedule -> {
            this.movieScheduleService.save(schedule);
            var producerSaveTicketDTO =
                ProducerSaveTicketDTO.builder()
                    .price(request.getPrice())
                    .totalSeats(schedule.getRoom().getTotalSeatNumber())
                    .scheduleCode(schedule.getScheduleCode())
                    .build();
            log.info(" info {}", producerSaveTicketDTO);
            this.producerHandleTicketService.save(producerSaveTicketDTO);
          });

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

  @Override
  public BaseResponse<ScheduleDetailResponse> findDetailScheduleById(Long id) {
    var movieSchedule =
        this.movieScheduleService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND));
    MovieDTO movieDTO;
    try {
      movieDTO = movieService.findMovieById(movieSchedule.getMovieId());
    } catch (Exception e) {
      throw new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND);
    }
    var scheduleDetailResponse =
        ScheduleDetailResponse.builder()
            .startedAt(movieSchedule.getStartedAt())
            .finishedAt(movieSchedule.getFinishedAt())
            .movieName(movieDTO.getTitle())
            .moviePoster(movieDTO.getPoster())
            .theaterId(movieSchedule.getMovieId())
            .theaterLocation(movieSchedule.getTheaterLocationDTO())
            .theaterName(movieSchedule.getTheaterName())
            .roomId(movieSchedule.getRoomId())
            .roomDTO(movieSchedule.getRoomDTO())
            .build();

    return BaseResponse.build(scheduleDetailResponse, true);
  }

  @Override
  public BaseResponse<PaginationResponse<ScheduleResponse>> findByFilter(
      ScheduleCriteria criteria) {
    var sortByProperties = Sort.by("startedAt");
    var pageable =
        PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize(), sortByProperties);
    Specification<MovieSchedule> specification =
        MovieScheduleSpecification.hasStartedAt(criteria.getStartedAt())
            .and(MovieScheduleSpecification.hasTheaterId(criteria.getTheaterId()))
            .and(MovieScheduleSpecification.hasMovieId(criteria.getMovieId()));

    var movieSchedulePage = this.movieScheduleService.findAll(specification, pageable);

    return BaseResponse.build(
        PaginationResponse.<ScheduleResponse>builder()
            .data(
                movieSchedulePage
                    .get()
                    .map(
                        movieSchedule ->
                            ScheduleResponse.builder()
                                .id(movieSchedule.getId())
                                .roomId(movieSchedule.getRoomId())
                                .roomName(movieSchedule.getRoomName())
                                .startedAt(movieSchedule.getStartedAt())
                                .finishedAt(movieSchedule.getFinishedAt())
                                .build())
                    .toList())
            .currentPage(criteria.getCurrentPage())
            .totalElements(movieSchedulePage.getNumberOfElements())
            .totalPages(movieSchedulePage.getTotalPages())
            .build(),
        true);
  }

  @Override
  @Transactional
  public void doSchedule(UpsertScheduleRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var theater =
        this.theaterService
            .findById(request.getTheaterId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND));

    var isValidAdminTheater = theater.getDirectorId().equals(principal.getId());
    if (!isValidAdminTheater) throw new PermissionDeniedException(ErrorCode.NOT_ADMIN);

    var room =
        theater.getRooms().stream()
            .filter(theaterRoom -> theaterRoom.getId().equals(request.getRoomId()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ROOM_NOT_FOUND));

    var movieSchedule =
        MovieSchedule.builder()
            .movieId(request.getMovieId())
            .room(room)
            .startedAt(request.getStartedAt())
            .finishedAt(request.getFinishedAt())
            .theater(theater)
            .build();

    var isValidMovieSchedule =
        this.validateConflictMovieSchedule(
            request.getTheaterId(), request.getRoomId(), movieSchedule);
    if (!isValidMovieSchedule)
      throw new ConflictMovieScheduleException(ErrorCode.CONFLICT_SCHEDULE);

    this.movieScheduleService.save(movieSchedule);
  }

  @Override
  public Long convertScheduleCodeToId(String scheduleCode) {
    var schedule =
        this.movieScheduleService
            .findByCode(scheduleCode)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND));
    return schedule.getId();
  }

  private Boolean validateConflictMovieSchedule(
      Long theaterId, Long roomId, MovieSchedule movieSchedule) {
    List<MovieSchedule> movieSchedules =
        this.movieScheduleService.findMovieSchedulesByDateAndTheaterIdAndRoomId(
            movieSchedule.getStartedAt(), theaterId, roomId);
    log.info("data in db {}", movieSchedules);
    if (movieSchedules == null || movieSchedules.isEmpty()) return false;

    var startedAt = movieSchedule.getStartedAtToLocalDateTime();
    var finishedAt = movieSchedule.getFinishedAtToLocalDateTime();

    return movieSchedules.stream()
        .anyMatch(
            ms -> {
              var isBetweenOfStartedAt =
                  ((startedAt.isBefore(ms.getStartedAtToLocalDateTime())
                          || startedAt.isEqual(ms.getStartedAtToLocalDateTime()))
                      || (startedAt.isAfter(ms.getFinishedAtToLocalDateTime())
                          || startedAt.isEqual(ms.getFinishedAtToLocalDateTime())));
              var isBetweenOfFinishedAt =
                  ((finishedAt.isBefore(ms.getStartedAtToLocalDateTime())
                          || finishedAt.isEqual(ms.getStartedAtToLocalDateTime()))
                      || (finishedAt.isAfter(ms.getFinishedAtToLocalDateTime())
                          || finishedAt.isEqual(ms.getFinishedAtToLocalDateTime())));
              log.info(
                  " {} {} check  {}  {}",
                  startedAt,
                  finishedAt,
                  ms.getStartedAtToLocalDateTime(),
                  ms.getFinishedAtToLocalDateTime());

              return isBetweenOfStartedAt || isBetweenOfFinishedAt;
            });
  }
}
