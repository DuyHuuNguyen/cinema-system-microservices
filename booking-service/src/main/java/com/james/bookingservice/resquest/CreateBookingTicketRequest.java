package com.james.bookingservice.resquest;

import com.james.bookingservice.dto.FoodIdAndQuantityDTO;
import com.james.bookingservice.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingTicketRequest {
  @Schema(defaultValue = "[43238,43239]")
  private List<Long> ticketIds;

  @Schema(
      defaultValue =
          "[\n"
              + "    {\n"
              + "      \"foodId\": 11,\n"
              + "      \"quantity\": 2\n"
              + "    }\n"
              + "  ]")
  private List<FoodIdAndQuantityDTO> foodIdAndQuantityDTOS;

  private PaymentType paymentType;

  private Long paymentId;

  private String bookingCode;

  @Schema(defaultValue = "[]")
  private List<Long> voucherIds;

  @Schema(defaultValue = "1")
  private Long theaterId;
}
