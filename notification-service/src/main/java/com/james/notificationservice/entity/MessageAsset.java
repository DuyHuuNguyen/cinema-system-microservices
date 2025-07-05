package com.james.notificationservice.entity;

import com.james.notificationservice.enums.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "message_assets")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MessageAsset extends BaseEntity {
  @Column(name = "media_key", nullable = false)
  private String mediaKey;

  @Column(name = "media_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private MediaType mediaType;

  @ManyToOne
  @JoinColumn(name = "message_id", nullable = false)
  private Message message;
}
