package com.james.scheduleservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.james.scheduleservice.dto.LocationDTO;
import com.james.scheduleservice.dto.RoomDTO;
import com.james.scheduleservice.until.TimeConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
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

  @Column(name = "schedule_code")
  @Builder.Default
  private String scheduleCode = UUID.randomUUID().toString();

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "theater_id", nullable = false)
  @JsonManagedReference
  private Theater theater;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "room_id", nullable = false)
  @JsonManagedReference
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

  public RoomDTO getRoomDTO() {
    return room.getRoomDTO();
  }

  private String theaterName() {
    return theater.getTheaterName();
  }

  public LocationDTO getTheaterLocationDTO() {
    return this.theater.getLocationDTO();
  }

  public String getRoomName() {
    return this.room.getRoomName();
  }

  public boolean isTheaterId(Long theaterId) {
    return this.theater.getId().equals(theaterId);
  }
}
