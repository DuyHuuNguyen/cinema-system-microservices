package com.james.scheduleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "rooms")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {
  @Column(name = "room_name", nullable = false)
  private String roomName;

  @Column(name = "monitor_width", nullable = false)
  private Integer monitorWidth;

  @Column(name = "monitor_height", nullable = false)
  private Integer monitorHeight;

  @Column(name = "seat_number", nullable = false)
  private Integer seatNumber;

  @ManyToOne
  @JoinColumn(name = "theater_id", nullable = false)
  private Theater theater;
}
