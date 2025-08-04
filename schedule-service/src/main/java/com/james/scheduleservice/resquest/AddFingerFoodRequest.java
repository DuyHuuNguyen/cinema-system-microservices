package com.james.scheduleservice.resquest;

import com.james.scheduleservice.dto.FingerFoodDTO;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddFingerFoodRequest {
  @Hidden private Long theaterId;
  private List<FingerFoodDTO> fingerFoodDTOS;

  public void attachTheaterId(Long theaterId) {
    this.theaterId = theaterId;
  }
}
