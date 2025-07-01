package com.james.identificationservice.request;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
  private String email;
  private String password;
}
