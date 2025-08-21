package com.james.bookingservice.facade;

import com.james.bookingservice.request.ChangePriceTicketsRequest;
import com.james.bookingservice.request.CreateTicketInternalRequest;
import com.james.bookingservice.request.ReleaseTicketsRequest;
import com.james.bookingservice.request.UpsertTicketRequest;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.TicketResponse;
import java.util.List;

public interface TicketFacade {
  void createTicketInternal(CreateTicketInternalRequest request);

  void deleteById(Long id);

  void changeTicketsPrice(ChangePriceTicketsRequest request);

  void releaseTickets(ReleaseTicketsRequest request);

  BaseResponse<List<TicketResponse>> findAllTicket(Long scheduleId);

  void updateTicket(UpsertTicketRequest request);
}
