package com.james.bookingservice.service;

import com.james.bookingservice.entity.Ticket;
import java.util.List;

public interface ConsumerHandleTicketService {
  void save(List<Ticket> tickets);
}
