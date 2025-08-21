package com.james.bookingservice.dto;

import com.james.bookingservice.enums.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FoodDTO {
  private Long id;
  private String foodName;
  private FoodType foodType;
  private Float price;
  private Integer quantity;
}
