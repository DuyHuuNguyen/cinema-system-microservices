package com.james.bookingservice.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpsertTicketRequest {
  @Hidden private Long id;
  private Float price;
  private Integer seatNumber;
  private Boolean isUsed;
  private String seatCode;
  private Long scheduleId;
  private Long theaterId;

  public void withId(Long id) {
    this.id = id;
  }
}
