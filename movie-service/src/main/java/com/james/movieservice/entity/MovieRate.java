package com.james.movieservice.entity;

import com.james.movieservice.dto.UpdateRateDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "movie_rates")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRate extends BaseEntity {
  @Column(name = "owner_id", nullable = false)
  private Long ownerId;

  @Column(name = "star_number", nullable = false)
  @Builder.Default
  private Integer starNumber = 0;

  @Column(name = "comment")
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;

  @OneToMany(
      mappedBy = "movieRate",
      orphanRemoval = true,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @Builder.Default
  private List<MovieRateAsset> movieRateAssets = new ArrayList<>();

  public void addAsset(MovieRateAsset movieRateAsset) {
    this.movieRateAssets.add(movieRateAsset);
  }

  public void updateInfo(UpdateRateDTO updateRateDTO) {
    this.starNumber = updateRateDTO.getNumberOfStars();
    this.comment = updateRateDTO.getComment();
    this.movieRateAssets.clear();
    updateRateDTO.getRateAssetDTOS().stream()
        .map(
            rateAssetDTO ->
                MovieRateAsset.builder()
                    .movieRate(this)
                    .mediaKey(rateAssetDTO.getMediaKey())
                    .mediaType(rateAssetDTO.getMediaType())
                    .build())
        .forEach(movieRateAssets::add);
  }
}
