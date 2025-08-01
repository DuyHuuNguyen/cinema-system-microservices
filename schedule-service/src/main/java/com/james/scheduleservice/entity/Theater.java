package com.james.scheduleservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "theaters")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Theater extends BaseEntity {

  @Column(name = "theater_name", nullable = false)
  private String theaterName;

  @Column(name = "description")
  private String description;

  @Column(name = "director_id")
  private Long directorId;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;

  @OneToMany(mappedBy = "theater")
  @Builder.Default
  private List<Room> rooms = new ArrayList<>();

  @OneToMany(mappedBy = "theater", orphanRemoval = true)
  @Builder.Default
  private List<TheaterRate> theaterRates = new ArrayList<>();

  @OneToMany(mappedBy = "theater", orphanRemoval = true)
  @Builder.Default
  private List<FingerFood> fingerFoods = new ArrayList<>();
}
