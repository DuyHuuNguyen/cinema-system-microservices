package com.james.bookingservice.request;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpsertVoucherRequest {
  @Hidden private Long id;

  @NotBlank(message = "Voucher type is required")
  private String voucherType;

  @NotNull(message = "Percent is required")
  @DecimalMin(value = "0.0", inclusive = true, message = "Percent must be >= 0")
  @DecimalMax(value = "100.0", inclusive = true, message = "Percent must be <= 100")
  private Float percent;

  @NotNull(message = "Max price is required")
  @Positive(message = "Max price must be greater than 0")
  private Float maxPrice;

  @NotNull(message = "ExpiredAt is required")
  private Long expiredAt;

  @NotBlank(message = "Voucher code is required")
  private String voucherCode;

  private String description;

  @NotNull(message = "TheaterId is required")
  private Long theaterId;

  @NotNull(message = "Quality is required")
  @Positive(message = "Quality must be greater than 0")
  private Integer quality;

  public void withId(Long id) {
    this.id = id;
  }
}
