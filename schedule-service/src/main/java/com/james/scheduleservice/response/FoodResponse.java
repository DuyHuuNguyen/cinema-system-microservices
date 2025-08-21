package com.james.scheduleservice.response;

import com.james.scheduleservice.dto.AssetDTO;
import com.james.scheduleservice.enums.FoodType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FoodResponse {
  private Long id;
  private String foodName;
  private FoodType foodType;
  private Float price;
  private List<AssetDTO> foodAssets;
}
