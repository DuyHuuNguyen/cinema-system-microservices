package com.james.movieservice.response;

import com.james.movieservice.dto.RateAssetDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RateResponse {
  private Long ownerId;
  private String comment;
  private Integer numberOfStar;
  private List<RateAssetDTO> rateAssetDTOS;
}
