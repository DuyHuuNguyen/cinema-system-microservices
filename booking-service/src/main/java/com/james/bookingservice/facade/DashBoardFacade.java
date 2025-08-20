package com.james.bookingservice.facade;

import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;
import com.james.bookingservice.resquest.BookingCriteria;

public interface DashBoardFacade {
  BaseResponse<PaginationResponse<BookingResponse>> findByFilter(BookingCriteria criteria);
}
