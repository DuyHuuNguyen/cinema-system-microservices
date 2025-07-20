package com.james.movieservice.entity;

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

  @OneToMany(mappedBy = "movieRate", orphanRemoval = true)
  @Builder.Default
  private List<MovieRateAsset> movieRateAssets = new ArrayList<>();

  public void addAsset(MovieRateAsset movieRateAsset) {
    this.movieRateAssets.add(movieRateAsset);
  }
}
