package com.james.notificationservice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "messages")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

  @Column(name = "subject", nullable = false)
  private String subject;

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "status", nullable = false)
  private String status;

  @OneToMany(mappedBy = "message", orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
  @Builder.Default
  private List<Receiver> receivers = new ArrayList<>();

  @OneToMany(mappedBy = "message", orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
  @Builder.Default
  private List<MessageAsset> messageAssets = new ArrayList<>();
}
