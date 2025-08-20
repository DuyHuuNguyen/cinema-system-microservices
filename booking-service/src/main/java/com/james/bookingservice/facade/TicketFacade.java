package com.james.bookingservice.facade;

import com.james.bookingservice.resquest.ChangePriceTicketsRequest;
import com.james.bookingservice.resquest.CreateTicketInternalRequest;

public interface TicketFacade {
  void createTicketInternal(CreateTicketInternalRequest request);

  void deleteById(Long id);

  void changeTicketsPrice(ChangePriceTicketsRequest request);
}
