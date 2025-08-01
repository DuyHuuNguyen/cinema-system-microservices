package com.james.identificationservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RefreshTokenResponse {
  private String accessToken;
  private String refreshToken;
}
