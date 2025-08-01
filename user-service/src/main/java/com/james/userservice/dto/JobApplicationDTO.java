package com.james.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobApplicationDTO {
  private Long userId;
  private Long theaterId;
  private String theaterName;
}
