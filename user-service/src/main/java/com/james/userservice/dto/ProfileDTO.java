package com.james.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProfileDTO {
  private String firstName;
  private String lastName;
  private String avatarUrl;
  private Long dateOfBirth;
}
