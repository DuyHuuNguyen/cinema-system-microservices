package com.james.movieservice.entity;

import com.james.movieservice.dto.RateAssetDTO;
import com.james.movieservice.enums.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "movie_rate_assets")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRateAsset extends BaseEntity {

  @Column(name = "media_key")
  private String mediaKey;

  @Column(name = "media_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private MediaType mediaType;

  @ManyToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
  @JoinColumn(name = "movie_rate_id", nullable = false)
  private MovieRate movieRate;

  public RateAssetDTO getRateAssetDTO() {
    return RateAssetDTO.builder().mediaKey(mediaKey).mediaType(mediaType).build();
  }
}
