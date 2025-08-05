package com.james.scheduleservice.service;

import com.james.scheduleservice.dto.ProducerSaveTicketDTO;

public interface ConsumerHandleTicketService {
  void saveTicket(ProducerSaveTicketDTO producerSaveTicketDTO);
}
