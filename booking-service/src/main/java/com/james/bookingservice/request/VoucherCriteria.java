package com.james.bookingservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoucherCriteria extends BaseCriteria {
  private Long theaterId;
}
