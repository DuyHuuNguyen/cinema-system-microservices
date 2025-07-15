package com.james.identificationservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VerifyOTPRequest {
  private String email;
  private String otp;
}
