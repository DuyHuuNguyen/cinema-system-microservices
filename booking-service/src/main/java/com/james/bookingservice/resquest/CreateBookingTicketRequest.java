package com.james.bookingservice.resquest;

import com.james.bookingservice.enums.PaymentType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingTicketRequest {
  private List<Long> ticketIds;
  private List<Long> foodIds;
  private PaymentType paymentType;
  private Long paymentId;
  private String bookingCode;
  private List<Long> voucherId;
  private Long theaterId;
}
