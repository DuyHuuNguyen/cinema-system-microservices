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

  @Column(name = "payment_id")
  private Long paymentId;

  @Column(name = "theater_id")
  private Long theaterId;

  @Column(name = "booking_code")
  private String bookingCode;

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  @JoinTable(
      name = "booking_tickets",
      joinColumns = @JoinColumn(name = "booking_id"),
      inverseJoinColumns = @JoinColumn(name = "ticket_id"))
  @Builder.Default
  private List<Ticket> tickets = new ArrayList<>();

  @OneToMany(
      mappedBy = "booking",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  @Builder.Default
  private List<BookingFingerFood> bookingFingerFoods = new ArrayList<>();

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  @JoinTable(
      name = "booking_vouchers",
      joinColumns = @JoinColumn(name = "booking_id"),
      inverseJoinColumns = @JoinColumn(name = "voucher_id"))
  private List<Voucher> vouchers = new ArrayList<>();

  public void addVoucher(Voucher voucher) {
    vouchers.add(voucher);
    voucher.addBooking(this);
  }

  public void addTicket(Ticket ticket) {
    tickets.add(ticket);
  }

  public void addBookingFingerFood(BookingFingerFood bookingFingerFood) {
    this.bookingFingerFoods.add(bookingFingerFood);
    bookingFingerFood.addBooking(this);
  }

  public void addBookingCode(String bookingCode) {
    this.bookingCode = bookingCode;
  }

  public void addPaymentId(Long paymentId) {
    this.paymentId = paymentId;
  }
}
