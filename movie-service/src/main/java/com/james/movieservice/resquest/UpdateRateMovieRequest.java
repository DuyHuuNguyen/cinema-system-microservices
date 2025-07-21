package com.james.movieservice.resquest;

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
public class UpdateRateMovieRequest {
  private Long rateId;
  private Integer numberOfStars;
  private String comment;
  private List<RateAssetDTO> rateAssetDTOS;
}
