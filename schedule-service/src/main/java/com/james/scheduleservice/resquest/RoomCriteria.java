package com.james.scheduleservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCriteria extends BaseCriteria {
  private Long theaterId;
}
