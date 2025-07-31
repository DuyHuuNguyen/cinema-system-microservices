package com.james.scheduleservice.dto;

import com.james.scheduleservice.enums.FoodType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FingerFoodDTO {
  private String foodName;
  private FoodType foodType;
  private Float price;
  private List<AssetDTO> foodAssetDTOS;
}
