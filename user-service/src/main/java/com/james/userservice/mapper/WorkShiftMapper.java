package com.james.userservice.mapper;

import com.james.userservice.entity.WorkShift;
import com.james.userservice.response.WorkShiftResponse;

public interface WorkShiftMapper {
  WorkShiftResponse toWorkPlaceResponse(WorkShift workShift);
}
