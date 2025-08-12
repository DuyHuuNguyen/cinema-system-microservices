package com.james.bookingservice.facade;

import com.james.bookingservice.resquest.CreateBookingTicketRequest;

public interface BookingFacade {
  void createBooking(CreateBookingTicketRequest request);
}
