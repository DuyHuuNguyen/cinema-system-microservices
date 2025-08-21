package com.james.bookingservice.request;

import com.james.bookingservice.enums.PaymentStatus;
import com.james.bookingservice.enums.PaymentType;
import lombok.*;

@ToString
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
