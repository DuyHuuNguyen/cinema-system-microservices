package com.james.userservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkShiftResponse {
  private Long id;
  private Long leaveForWorkedAt;
  private Long leaveWorkedAt;
  private Long checkedInAt;
  private Long checkedOutAt;
}
