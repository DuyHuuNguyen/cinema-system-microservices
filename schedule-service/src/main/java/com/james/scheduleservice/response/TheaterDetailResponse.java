package com.james.scheduleservice.response;

import com.james.scheduleservice.dto.AssetDTO;
import com.james.scheduleservice.dto.LocationDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TheaterDetailResponse {
  private String theaterName;
  private String description;
  private LocationDTO locationDTO;
  private List<AssetDTO> theaterAssetDTOS;
  private Double averageStars;
}
