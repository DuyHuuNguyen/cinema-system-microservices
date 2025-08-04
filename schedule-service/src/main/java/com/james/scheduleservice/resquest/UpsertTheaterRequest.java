package com.james.scheduleservice.resquest;

import com.james.scheduleservice.dto.AssetDTO;
import com.james.scheduleservice.dto.FingerFoodDTO;
import com.james.scheduleservice.dto.LocationDTO;
import com.james.scheduleservice.dto.RoomDTO;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertTheaterRequest {
  @Hidden private Long id;
  @NonNull private String theaterName;
  @NonNull private Long directorId;
  @NonNull private LocationDTO locationDTO;
  private List<AssetDTO> theaterAssetDTOS;
  private List<RoomDTO> roomDTOS;
  private List<FingerFoodDTO> fingerFoodDTOS;

  public void attachId(Long theaterId) {
    this.id = theaterId;
  }
}
