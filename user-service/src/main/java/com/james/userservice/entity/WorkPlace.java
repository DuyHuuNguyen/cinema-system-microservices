package com.james.userservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "work_places")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WorkPlace extends BaseEntity {

  @ManyToOne(
      cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.PERSIST,
        CascadeType.REFRESH
      })
  @JoinColumn(name = "employee_id", nullable = false)
  private User employee;

  @Column(name = "theater_id", nullable = false)
  private Long theaterId;

  @OneToMany(
      mappedBy = "workPlace",
      cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.PERSIST,
        CascadeType.REFRESH
      })
  @Builder.Default
  private List<WorkShift> workShifts = new ArrayList<>();
}
