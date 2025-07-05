package com.james.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
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

  @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
  private User user;

  public void addUser(User user) {
    this.user = user;
  }
}
