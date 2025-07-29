package com.james.scheduleservice.entity;

import com.james.scheduleservice.dto.LocationDTO;
import com.james.scheduleservice.until.TimeConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@Table(name = "movie_schedules")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSchedule extends BaseEntity {
  @Column(name = "started_at", nullable = false)
  private Long startedAt;

  @Column(name = "finished_at", nullable = false)
  private Long finishedAt;

  @Column(name = "movie_id", nullable = false)
  private Long movieId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "theater_id", nullable = false)
  private Theater theater;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;

  public String getTheaterName() {
    return this.theater.getTheaterName();
  }

  public LocationDTO getLocationTheater() {
    return this.theater.getLocationDTO();
  }

  public void addMovieId(Long id) {
    this.movieId = id;
  }

  public LocalDateTime getStartedAtToLocalDateTime() {
    return TimeConverter.convertStringToDateTime(this.startedAt);
  }

  public LocalDateTime getFinishedAtToLocalDateTime() {
    return TimeConverter.convertStringToDateTime(this.finishedAt);
  }

  public Long getRoomId() {
    return this.room.getId();
  }
}
