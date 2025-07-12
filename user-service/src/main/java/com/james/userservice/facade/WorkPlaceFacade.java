package com.james.userservice.facade;

import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.PaginationWorkShiftResponse;
import com.james.userservice.response.TheaterProfileResponse;
import com.james.userservice.response.WorkShiftResponse;
import com.james.userservice.resquest.CheckInWorkShiftRequest;
import com.james.userservice.resquest.WorkShiftRequest;

public interface WorkPlaceFacade {
  BaseResponse<PaginationWorkShiftResponse<WorkShiftResponse, TheaterProfileResponse>>
      findMyWordShift(WorkShiftRequest request);

  void checkInWorkShift(CheckInWorkShiftRequest request);
}
