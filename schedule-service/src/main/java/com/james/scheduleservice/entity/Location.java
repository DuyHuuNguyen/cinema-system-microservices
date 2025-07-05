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
@Table(name = "locations")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity {
  @Column(name = "longitude", nullable = false)
  private Long longitude;

  @Column(name = "latitude", nullable = false)
  private Long latitude;

  @OneToMany @Builder.Default private List<Theater> theater = new ArrayList<>();
}
