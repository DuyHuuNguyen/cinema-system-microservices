package com.james.bookingservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class VoucherResponse {
  private Long id;
  private String voucherType;
  private Float percent;
  private Float maxPrice;
  private Long expiredAt;
  private String voucherCode;
  private String description;
  private Long theaterId;
  private Integer quality;
  private Long createdAt;
}
