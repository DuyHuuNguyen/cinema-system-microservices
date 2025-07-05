package com.james.bookingservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "bookings")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseEntity {
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "payment_id", nullable = false)
  private Long paymentId;

  @ManyToMany
  @JoinTable(
      name = "booking_tickets",
      joinColumns = @JoinColumn(name = "booking_id"),
      inverseJoinColumns = @JoinColumn(name = "ticket_id"))
  @Builder.Default
  private List<Ticket> tickets = new ArrayList<>();

  @OneToMany(mappedBy = "booking")
  private List<BookingFingerFood> bookingFingerFoods;

  @ManyToMany
  @JoinTable(
      name = "booking_vouchers",
      joinColumns = @JoinColumn(name = "booking_id"),
      inverseJoinColumns = @JoinColumn(name = "voucher_id"))
  private List<Voucher> vouchers = new ArrayList<>();
}
