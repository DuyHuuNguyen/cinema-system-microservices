package com.james.scheduleservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidAdminTheaterRequest {
  private Long adminId;
  private Long theaterId;
}
