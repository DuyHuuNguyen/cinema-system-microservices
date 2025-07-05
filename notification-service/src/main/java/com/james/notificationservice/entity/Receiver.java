package com.james.notificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "receivers")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Receiver extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "message_id", nullable = false)
  private Message message;

  @Column(name = "user_id", nullable = false)
  private Long userId;
}
