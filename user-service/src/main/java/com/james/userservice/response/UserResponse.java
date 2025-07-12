package com.james.userservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserResponse {
  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private String avatarKey;
  private Long dateOfBirth;
}
