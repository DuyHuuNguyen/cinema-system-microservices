package com.james.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class VoucherDTO {
  private String voucherType;
  private Float percent;
  private Float maxPrice;
  private Long expiredAt;
  private String voucherCode;
  private String description;
  private Integer quality;
}
