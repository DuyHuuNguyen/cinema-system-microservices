package com.james.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

  @ManyToOne private User user;
}
