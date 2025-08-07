package com.james.scheduleservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.james.scheduleservice.enums.*;
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
  @JsonBackReference
  private Theater theater;

  public void addTheater(Theater theater) {
    this.theater = theater;
  }

  public boolean isImage() {
    return this.mediaType.isImage();
  }
}
