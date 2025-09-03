package com.james.paymentservice.entity;

import com.james.paymentservice.enums.WalletStatus;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@SuperBuilder
@Entity
@Table(name = "wallets")
public class Wallet extends BaseEntity {
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "wallet_name")
  private String walletName;

  @Column(name = "balance")
  @Builder.Default
  private Double balance = 0.0;

  @Column(name = "currency")
  private String currency;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private WalletStatus status;

  @OneToMany(
      mappedBy = "sourceWallet",
      fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Transaction> sendTransactions = new ArrayList<>();

  @OneToMany(
      mappedBy = "destinationWallet",
      fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Transaction> receiveTransactions = new ArrayList<>();

  //  public void addTransaction(Transaction transaction) {
  //    this.transactions.add(transaction);
  //  }

  public void minusBalance(Double balance) {
    this.balance -= balance;
  }

  public void plusBalance(Double balance) {
    this.balance += balance;
  }

  public void changeWalletName(String walletName) {
    this.walletName = walletName;
  }

  public void changeStatus(WalletStatus status) {
    this.status = status;
  }
}
