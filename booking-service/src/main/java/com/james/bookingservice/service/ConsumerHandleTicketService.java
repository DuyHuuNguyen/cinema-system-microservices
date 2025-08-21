package com.james.bookingservice.service;

import com.james.bookingservice.entity.Ticket;

public interface ConsumerHandleTicketService {
  void save(Ticket ticket);
}
