package com.james.movieservice.resquest;

import com.james.movieservice.dto.RateAssetDTO;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RateMovieRequest {
  @Hidden private Long movieId;
  private Integer numberOfStars;
  private String comment;
  private List<RateAssetDTO> rateAssetDTOS;

  public void attachMovieId(Long id) {
    this.movieId = id;
  }
}
