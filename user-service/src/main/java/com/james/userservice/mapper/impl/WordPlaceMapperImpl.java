package com.james.userservice.mapper.impl;

import com.james.userservice.entity.WorkShift;
import com.james.userservice.mapper.WorkShiftMapper;
import com.james.userservice.response.WorkShiftResponse;
import org.springframework.stereotype.Service;

@Service
public class WordPlaceMapperImpl implements WorkShiftMapper {
  @Override
  public WorkShiftResponse toWorkPlaceResponse(WorkShift workShift) {
    return WorkShiftResponse.builder()
        .id(workShift.getId())
        .leaveForWorkedAt(workShift.getLeaveForWorkedAt())
        .leaveWorkedAt(workShift.getLeaveWorkedAt())
        .checkedInAt(workShift.getCheckedInAt())
        .checkedOutAt(workShift.getCheckedOutAt())
        .build();
  }
}
