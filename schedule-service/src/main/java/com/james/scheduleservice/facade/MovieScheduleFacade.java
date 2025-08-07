package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.response.*;
import com.james.scheduleservice.resquest.DoScheduleRequest;
import com.james.scheduleservice.resquest.ScheduleCriteria;
import com.james.scheduleservice.resquest.UpsertScheduleRequest;

public interface MovieScheduleFacade {
  ScheduleDTO findScheduleById(Long id);

  BaseResponse<DoScheduleResponse> doSchedules(DoScheduleRequest request);

  void deleteScheduleById(Long id, Long theaterId);

  BaseResponse<ScheduleDetailResponse> findDetailScheduleById(Long id);

  BaseResponse<PaginationResponse<ScheduleResponse>> findByFilter(ScheduleCriteria criteria);

  void doSchedule(UpsertScheduleRequest request);

  Long convertScheduleCodeToId(String scheduleCode);
}
