package com.james.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FoodIdAndQuantityDTO {
  private Long foodId;
  private int quantity;
}
