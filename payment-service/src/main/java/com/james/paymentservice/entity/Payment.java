package com.james.paymentservice.entity;

import com.james.paymentservice.enums.PaymentStatus;
import com.james.paymentservice.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@Table(name = "payments")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {

  @Column(name = "payment_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private PaymentType paymentType;

  @Column(name = "payment_status", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private PaymentStatus paymentStatus;

  @Column(name = "booking_id", nullable = false)
  private Long bookingId;

  @Column(name = "price", nullable = false)
  private float price;

  public Boolean isCompletedPayment() {
    return paymentStatus.isCompleted();
  }

  public void addBookingId(Long bookingId) {
    this.bookingId = bookingId;
  }
}
