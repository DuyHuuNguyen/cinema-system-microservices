package com.james.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "work_shifts")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WorkShift extends BaseEntity {
  @Column(name = "leave_for_worked_at", nullable = false)
  private Long leaveForWorkedAt;

  @Column(name = "leave_worked_at", nullable = false)
  private Long leaveWorkedAt;

  @Column(name = "checked_in_at", nullable = false)
  @Builder.Default
  private Long checkedInAt = 0L;

  @Column(name = "checked_out_at", nullable = false)
  @Builder.Default
  private Long checkedOutAt = 0L;

  @ManyToOne
  @JoinColumn(name = "work_place_id")
  private WorkPlace workPlace;

  public void checkIn(long now) {
    this.checkedInAt = now;
  }
}
