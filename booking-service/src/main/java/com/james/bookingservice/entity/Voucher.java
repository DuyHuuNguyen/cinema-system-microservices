package com.james.bookingservice.entity;

import com.james.bookingservice.dto.VoucherDTO;
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

  @Column(name = "theater_id", nullable = false)
  private Long theaterId;

  @Column(name = "quality", nullable = false)
  private Integer quality;

  @ManyToOne private Booking booking;

  public void addBooking(Booking booking) {
    this.booking = booking;
  }

  public Boolean isExpired() {
    return expiredAt <= System.currentTimeMillis();
  }

  public void subQuality() {
    this.quality--;
  }

  public void changeInfo(VoucherDTO voucherDTO) {
    this.voucherType = voucherDTO.getVoucherType();
    this.percent = voucherDTO.getPercent();
    this.maxPrice = voucherDTO.getMaxPrice();
    this.expiredAt = voucherDTO.getExpiredAt();
    this.voucherCode = voucherDTO.getVoucherCode();
    this.description = voucherDTO.getDescription();
    this.quality = voucherDTO.getQuality();
  }
}
