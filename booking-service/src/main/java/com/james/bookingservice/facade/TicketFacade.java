package com.james.bookingservice.facade;

import com.james.bookingservice.resquest.ChangePriceTicketsRequest;
import com.james.bookingservice.resquest.CreateTicketInternalRequest;
import com.james.bookingservice.resquest.ReleaseTicketsRequest;

public interface TicketFacade {
  void createTicketInternal(CreateTicketInternalRequest request);

  void deleteById(Long id);

  void changeTicketsPrice(ChangePriceTicketsRequest request);

  void releaseTickets(ReleaseTicketsRequest request);
}
