package com.james.bookingservice.facade;

import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.resquest.BookingCriteria;
import com.james.bookingservice.resquest.CreateBookingTicketRequest;

public interface BookingFacade {
  void createBooking(CreateBookingTicketRequest request);

  BaseResponse<PaginationResponse<BookingResponse>> findByFilter(BookingCriteria criteria);
}
