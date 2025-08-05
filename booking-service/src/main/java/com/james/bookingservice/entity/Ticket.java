package com.james.bookingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tickets")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends BaseEntity {

  @Column(name = "price", nullable = false)
  private Float price;

  @Column(name = "seat_number", nullable = false)
  private Integer seatNumber;

  @Column(name = "seat_code", nullable = false)
  @Builder.Default
  private String seatCode = UUID.randomUUID().toString().substring(0, 5);

  @Column(name = "schedule_id", nullable = false)
  private Long scheduleId;
}
