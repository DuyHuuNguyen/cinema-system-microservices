package com.james.bookingservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "bookings")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BookingFingerFood extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "booking_id", nullable = false)
  private Booking booking;

  @Column(name = "finger_food_id", nullable = false)
  private Long fingerFoodId;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;
}
