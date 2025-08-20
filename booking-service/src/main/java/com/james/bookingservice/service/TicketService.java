package com.james.bookingservice.service;

import com.james.bookingservice.entity.Ticket;
import java.util.List;
import java.util.Optional;

public interface TicketService {
  void save(Ticket ticket);

  Optional<Ticket> findById(Long id);

  void delete(Ticket ticket);

  List<Ticket> findTicketsByScheduleId(Long scheduleId);
}
