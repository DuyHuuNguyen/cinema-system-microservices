package com.james.paymentservice.dto;

import com.james.paymentservice.enums.PaymentStatus;
import com.james.paymentservice.enums.PaymentType;
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
