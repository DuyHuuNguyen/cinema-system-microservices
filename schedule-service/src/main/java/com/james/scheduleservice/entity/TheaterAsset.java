package com.james.scheduleservice.entity;

import com.james.movieservice.enums.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "theater_assets")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterAsset extends BaseEntity {
  @Column(name = "media_key")
  private String mediaKey;

  @Column(name = "media_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private MediaType mediaType;

  @ManyToOne
  @JoinColumn(name = "theater_id")
  private Theater theater;

  public void addTheater(Theater theater) {
    this.theater = theater;
  }
}
