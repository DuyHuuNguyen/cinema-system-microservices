package com.james.scheduleservice.until;

import com.james.scheduleservice.dto.AllocateScreeningDTO;
import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.entity.Room;
import com.james.scheduleservice.entity.Theater;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.exception.EntityNotFoundException;
import java.time.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TimeLineUtil {
  private LocalDateTime startedAt;
  private LocalDateTime finishedAt;

  private Long startMinute;
  private Long endMinute;
  private Long restMinute;
  private Long middleSection;
  private Queue<Room> rooms;
  private List<MovieUtil> movies;

  private Theater theater;

  private List<MovieSchedule> movieSchedules;

  private List<AllocateScreeningDTO> allocateScreeningDTOS;

  public static TimeLineUtil build(LocalDateTime startedAt, LocalDateTime endedAt) {
    return TimeLineUtil.builder()
        .startedAt(startedAt)
        .finishedAt(endedAt)
        .startMinute(convertTimeToMinute(startedAt))
        .endMinute(convertTimeToMinute(endedAt))
        .restMinute(convertTimeToMinute(endedAt) - convertTimeToMinute(startedAt) + 1L)
        .build();
  }

  public static Long convertTimeToMinute(LocalDateTime time) {
    return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }

  public void addRoom(Room room) {
    if (rooms == null || rooms.isEmpty()) rooms = new LinkedList<>();
    rooms.add(room);
  }

  public void addMovies(Queue<MovieUtil> moviesUtil) {
    int count = 0;
    while (!moviesUtil.isEmpty()) {
      if (this.restMinute > moviesUtil.peek().getDuration()) {
        this.addMovie(moviesUtil.peek());
        var firstMovie = moviesUtil.poll();
        moviesUtil.add(firstMovie);
      } else {
        var firstMovie = moviesUtil.poll();
        moviesUtil.add(firstMovie);
        count++;
      }
      if (count == moviesUtil.size()) break;
    }
  }

  public Boolean addMovie(MovieUtil movie) {
    if (movies == null || movies.isEmpty()) movies = new ArrayList<>();
    System.out.println(this.restMinute - movie.getDuration());
    var isStillWithinTheTimeLimit = this.restMinute - movie.getDuration() > 0;
    if (!isStillWithinTheTimeLimit) {
      return false;
    }
    this.restMinute -= movie.getDuration();
    return this.movies.add(movie);
  }

  public void addTheater(Theater theater) {
    this.theater = theater;
  }

  public void addMiddleSection(Long middleSection) {
    this.middleSection = middleSection;
  }

  public List<AllocateScreeningDTO> buildAllocateScreeningDTOS() {
    this.movieSchedules = new ArrayList<>();
    this.allocateScreeningDTOS = new ArrayList<>();
    Long start = this.startMinute;
    Long end = this.startMinute;
    ZoneId zone = ZoneId.systemDefault();

    var isValidMovie = this.movies != null || !this.movies.isEmpty();
    if (!isValidMovie) throw new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND);

    var isValidRoom = this.rooms != null || !this.rooms.isEmpty();
    if (!isValidRoom) throw new EntityNotFoundException(ErrorCode.ROOM_NOT_FOUND);

    for (var movie : this.movies) {
      var room = this.rooms.poll();
      rooms.add(room);
      end += movie.getDuration();

      MovieSchedule movieSchedule =
          MovieSchedule.builder()
              .startedAt(start)
              .finishedAt(end)
              .theater(theater)
              .room(room)
              .movieId(movie.getId())
              .build();

      movieSchedules.add(movieSchedule);

      allocateScreeningDTOS.add(
          AllocateScreeningDTO.builder()
              .movieId(movie.getId())
              .movieName(movie.getFirstImage())
              .roomId(room.getId())
              .roomName(room.getRoomName())
              .totalSeatNumber(room.getTotalSeatNumber())
              .startedAt(Instant.ofEpochMilli(start).atZone(zone).toLocalDateTime())
              .endedAt(Instant.ofEpochMilli(end).atZone(zone).toLocalDateTime())
              .build());

      start += movie.getDuration();
      start += this.middleSection;
    }

    return allocateScreeningDTOS;
  }
}
