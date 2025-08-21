package com.james.bookingservice.response;

import com.james.bookingservice.dto.FoodDTO;
import com.james.bookingservice.dto.PaymentDTO;
import com.james.bookingservice.dto.TicketDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingDetailResponse {
  private Long id;
  private Long userId;
  private Long paymentId;
  private Long theaterId;
  private String bookingCode;
  private Long createdAt;
  private List<FoodDTO> foodDTOs;
  private PaymentDTO paymentDTO;
  private List<TicketDTO> ticketDTOs;
}
