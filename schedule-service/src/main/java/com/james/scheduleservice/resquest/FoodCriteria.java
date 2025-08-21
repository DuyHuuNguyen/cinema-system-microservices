package com.james.scheduleservice.resquest;

import com.james.scheduleservice.enums.FoodType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCriteria extends BaseCriteria {
  private FoodType foodType;
  private Float price;
  @NotNull private Long theaterId;
}
