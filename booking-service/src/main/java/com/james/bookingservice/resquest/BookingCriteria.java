package com.james.bookingservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCriteria extends BaseCriteria {
  private Long theaterId;
  private Long aroundCreatedAt;
}
