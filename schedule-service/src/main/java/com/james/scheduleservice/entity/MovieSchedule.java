package com.james.scheduleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

  @ManyToOne
  @JoinColumn(name = "theater_id", nullable = false)
  private Theater theater;

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;
}
