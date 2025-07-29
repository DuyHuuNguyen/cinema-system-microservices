package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.response.*;
import com.james.scheduleservice.resquest.DoScheduleRequest;
import com.james.scheduleservice.resquest.ScheduleCriteria;

public interface MovieScheduleFacade {
  ScheduleDTO findScheduleById(Long id);

  BaseResponse<DoScheduleResponse> doSchedule(DoScheduleRequest request);

  void deleteScheduleById(Long id, Long theaterId);

  BaseResponse<ScheduleDetailResponse> findDetailScheduleById(Long id);

  BaseResponse<PaginationResponse<ScheduleResponse>> findByFilter(ScheduleCriteria criteria);
}
