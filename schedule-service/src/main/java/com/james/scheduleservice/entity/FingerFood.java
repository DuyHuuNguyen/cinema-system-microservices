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
@Table(name = "finger_foods")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FingerFood extends BaseEntity {
  @Column(name = "food_name", nullable = false)
  private String foodName;

  @Column(name = "food_type", nullable = false)
  private String foodType;

  @Column(name = "price", nullable = false)
  private Float price;

  @ManyToOne
  @JoinColumn(name = "theater_id")
  private Theater theater;

  @OneToMany(mappedBy = "fingerFood")
  @Builder.Default
  private List<FingerFoodAsset> fingerFoodAssets = new ArrayList<>();
}
