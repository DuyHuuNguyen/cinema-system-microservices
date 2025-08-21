package com.james.bookingservice.facade;

import com.james.bookingservice.request.BookingCriteria;
import com.james.bookingservice.response.BaseResponse;
import com.james.bookingservice.response.BookingDetailResponse;
import com.james.bookingservice.response.BookingResponse;
import com.james.bookingservice.response.PaginationResponse;

public interface DashBoardFacade {
  BaseResponse<PaginationResponse<BookingResponse>> findByFilter(BookingCriteria criteria);

  BaseResponse<BookingDetailResponse> findBookingDetailById(Long id);
}
