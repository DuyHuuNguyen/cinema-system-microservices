package com.james.scheduleservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class ProducerSaveTicketDTO {
  private Float price;
  private Integer totalSeats;
  private String scheduleCode;
}
