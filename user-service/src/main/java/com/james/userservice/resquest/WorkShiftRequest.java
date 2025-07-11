package com.james.userservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkShiftRequest extends BaseCriteria {
  private Long theaterId;
}
