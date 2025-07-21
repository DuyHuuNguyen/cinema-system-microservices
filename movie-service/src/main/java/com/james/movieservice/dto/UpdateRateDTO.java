package com.james.movieservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdateRateDTO {
  private Integer numberOfStars;
  private String comment;
  private List<RateAssetDTO> rateAssetDTOS;
}
