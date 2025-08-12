package com.james.paymentservice.resquest;

import com.james.paymentservice.enums.PaymentStatus;
import com.james.paymentservice.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertPaymentRequest {
  private PaymentType paymentType;
  private PaymentStatus paymentStatus;
  private Long bookingId;
  private float price;
}
