package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.response.DoScheduleResponse;
import com.james.scheduleservice.resquest.DoScheduleRequest;

public interface MovieScheduleFacade {
  ScheduleDTO findScheduleById(Long id);

  BaseResponse<DoScheduleResponse> doSchedule(DoScheduleRequest request);

  void deleteScheduleById(Long id, Long theaterId);
}
