package com.james.bookingservice.dto;

import com.james.bookingservice.enums.PaymentStatus;
import com.james.bookingservice.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentDTO {
  private Long id;
  private PaymentType paymentType;
  private PaymentStatus paymentStatus;
  private float price;
}
