package com.james.scheduleservice.entity;

import com.james.scheduleservice.dto.LocationDTO;
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

  @OneToOne(
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
      optional = false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @OneToMany(
      mappedBy = "theater",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
  @Builder.Default
  private List<TheaterAsset> theaterAssets = new ArrayList<>();

  @OneToMany(
      mappedBy = "theater",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
  @Builder.Default
  private List<Room> rooms = new ArrayList<>();

  @OneToMany(
      mappedBy = "theater",
      orphanRemoval = true,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
  @Builder.Default
  private List<TheaterRate> theaterRates = new ArrayList<>();

  @OneToMany(
      mappedBy = "theater",
      orphanRemoval = true,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
  @Builder.Default
  private List<FingerFood> fingerFoods = new ArrayList<>();

  public LocationDTO getLocationDTO() {
    return LocationDTO.builder()
        .longitude(location.getLongitude())
        .latitude(location.getLatitude())
        .build();
  }

  public void addRoom(Room room) {
    this.rooms.add(room);
    room.addTheater(this);
  }

  public void addFingerFood(FingerFood fingerFood) {
    this.fingerFoods.add(fingerFood);
    fingerFood.addTheater(this);
  }

  public void addLocation(Location location) {
    location.addTheater(this);
    this.location = location;
  }

  public void addTheaterAsset(TheaterAsset theaterAsset) {
    this.theaterAssets.add(theaterAsset);
    theaterAsset.addTheater(this);
  }

  public void removeAllRooms() {
    this.rooms.clear();
  }

  public void removeAllTheaterAssets() {
    this.theaterAssets.clear();
  }

  public void removeAllFingerFoods() {
    this.fingerFoods.clear();
  }

  public void changeTheaterName(String theaterName) {
    this.theaterName = theaterName;
  }
}
