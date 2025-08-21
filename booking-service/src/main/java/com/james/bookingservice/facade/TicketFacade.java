package com.james.bookingservice.facade;

import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.TicketResponse;
import com.james.bookingservice.resquest.ChangePriceTicketsRequest;
import com.james.bookingservice.resquest.CreateTicketInternalRequest;
import com.james.bookingservice.resquest.ReleaseTicketsRequest;
import java.util.List;

public interface TicketFacade {
  void createTicketInternal(CreateTicketInternalRequest request);

  void deleteById(Long id);

  void changeTicketsPrice(ChangePriceTicketsRequest request);

  void releaseTickets(ReleaseTicketsRequest request);

  BaseResponse<List<TicketResponse>> findAllTicket(Long scheduleId);
}
