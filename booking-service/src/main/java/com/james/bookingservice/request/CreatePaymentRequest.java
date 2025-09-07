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
  //  private Long destinationId;
  private PaymentType paymentType;
  private PaymentStatus paymentStatus;
  private Long bookingId;
  private float price;

  private Long sourceWalletId;
  private Long destinationWalletId;
  private Double amount;
  //      private TransactionType transactionType;
  //      private TransactionEnum transactionStatus;
  //      private Long bookingId;
}
