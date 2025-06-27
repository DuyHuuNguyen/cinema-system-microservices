package com.james.paymentservice.entity;

import com.james.paymentservice.enums.PaymentStatus;
import com.james.paymentservice.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
}
