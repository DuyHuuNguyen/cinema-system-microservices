package com.james.identificationservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResetPasswordRequest {
  private String newPassword;
  private String confirmPassword;
}
