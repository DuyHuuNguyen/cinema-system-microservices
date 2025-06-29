package com.james.scheduleservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "theater_rates")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterRate extends BaseEntity {
  @Column(name = "owner_id", nullable = false)
  private Long ownerId;

  @Column(name = "star_number", nullable = false)
  private Integer starNumber;

  @Column(name = "comment", nullable = false)
  private String comment;

  @ManyToOne
  @JoinColumn(name = "theater_id")
  private Theater theater;

  @OneToMany(mappedBy = "theaterRate")
  private List<TheaterRateAsset> theaterRateAssets = new ArrayList<>();
}
