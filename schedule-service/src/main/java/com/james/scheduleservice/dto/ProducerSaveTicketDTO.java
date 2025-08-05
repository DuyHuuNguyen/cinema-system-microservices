package com.james.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProducerSaveTicketDTO {
  private Float price;
  private Integer totalSeats;
  private Long scheduleId;
}
