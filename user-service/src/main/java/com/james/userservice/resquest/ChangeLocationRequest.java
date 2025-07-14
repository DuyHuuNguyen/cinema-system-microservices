package com.james.userservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangeLocationRequest {
  private Long longitude;
  private Long latitude;
}
