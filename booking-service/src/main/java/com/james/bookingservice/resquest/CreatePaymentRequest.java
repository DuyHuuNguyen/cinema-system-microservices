package com.james.bookingservice.resquest;

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
public class CreatePaymentRequest {
  private PaymentType paymentType;
  private PaymentStatus paymentStatus;
  private Long bookingId;
  private float price;
}
