package com.james.bookingservice.service.impl;

import com.james.bookingservice.entity.Ticket;
import com.james.bookingservice.repository.TicketRepository;
import com.james.bookingservice.service.TicketService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;

  @Override
  public void save(Ticket ticket) {
    this.ticketRepository.save(ticket);
  }

  @Override
  public Optional<Ticket> findById(Long id) {
    return this.ticketRepository.findById(id);
  }

  @Override
  public void delete(Ticket ticket) {
    this.ticketRepository.delete(ticket);
  }

  @Override
  public List<Ticket> findTicketsByScheduleId(Long scheduleId) {
    return this.ticketRepository.findTicketsByScheduleId(scheduleId);
  }
}
