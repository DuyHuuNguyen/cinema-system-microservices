package com.james.bookingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Vouchers")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Voucher extends BaseEntity {
  @Column(name = "type", nullable = false)
  private String voucherType;

  @Column(name = "percent", nullable = false)
  private Float percent;

  @Column(name = "max_price", nullable = false)
  private Float maxPrice;

  @Column(name = "expired_at", nullable = false)
  private Long expiredAt;

  @Column(name = "code", nullable = false)
  private String voucherCode;

  @Column(name = "description", nullable = false)
  private String description;

  @ManyToOne private Booking booking;

  public void addBooking(Booking booking) {
    this.booking = booking;
  }

  public Boolean isExpired() {
    return expiredAt <= System.currentTimeMillis();
  }
}
