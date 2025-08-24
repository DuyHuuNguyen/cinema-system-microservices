package com.james.paymentservice.entity;

import com.james.paymentservice.enums.TransactionEnum;
import com.james.paymentservice.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
  private Wallet wallet;

  @Column(name = "type")
  private TransactionType type;

  @Column(name = "amount", nullable = false)
  private Double amount;

  @Column(name = "status")
  @Builder.Default
  private TransactionEnum status = TransactionEnum.ERROR;

  @Column(name = "partner_id", nullable = false)
  private Long partnerId;
}
