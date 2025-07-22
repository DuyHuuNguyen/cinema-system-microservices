package com.james.scheduleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

  //  @OneToOne(mappedBy = "location",optional = false)
  //  private Theater theater;
  //  @OneToMany @Builder.Default private List<Theater> theaters = new ArrayList<>();
  //
  public void addTheater(Theater theater) {
    //    this.theater = theater;
  }
}
