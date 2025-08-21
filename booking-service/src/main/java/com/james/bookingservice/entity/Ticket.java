package com.james.bookingservice.entity;

import com.james.bookingservice.dto.TicketDTO;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Entity
@Table(name = "tickets")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends BaseEntity {

  @Column(name = "price", nullable = false)
  private Float price;

  @Column(name = "seat_number", nullable = false)
  private Integer seatNumber;

  @Column(name = "is_uesed")
  private Boolean isUsed;

  @Column(name = "seat_code", nullable = false)
  @Builder.Default
  private String seatCode = UUID.randomUUID().toString().substring(0, 5);

  @Column(name = "schedule_id", nullable = false)
  private Long scheduleId;

  public void markTicketUsed() {
    this.isUsed = true;
  }

  public void markTicketUnused() {
    this.isUsed = false;
  }

  public void changePrice(Float price) {
    this.price = price;
  }

  public void release() {
    this.isActive = true;
  }

  public void changeInfo(TicketDTO ticketDTO) {
    this.price = ticketDTO.getPrice();
    this.seatCode = ticketDTO.getSeatCode();
    this.seatNumber = ticketDTO.getSeatNumber();
  }
}
