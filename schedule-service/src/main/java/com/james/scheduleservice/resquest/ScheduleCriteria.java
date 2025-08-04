package com.james.scheduleservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleCriteria extends BaseCriteria {
  private Long StartedAt;
  private Long movieId;
  private Long theaterId;
}
