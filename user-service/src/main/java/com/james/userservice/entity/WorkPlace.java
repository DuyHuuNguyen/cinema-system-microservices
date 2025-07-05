package com.james.userservice.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
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

  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false)
  private User employee;

  @Column(name = "theater_id", nullable = false)
  private Long theaterId;

  @OneToMany(mappedBy = "workPlace")
  private List<WorkShift> workShifts;
}
