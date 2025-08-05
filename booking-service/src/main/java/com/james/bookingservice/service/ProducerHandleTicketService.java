package com.james.bookingservice.service;

import com.james.bookingservice.entity.Ticket;
import java.util.List;

public interface ProducerHandleTicketService {
  void save(List<Ticket> tickets);
}
