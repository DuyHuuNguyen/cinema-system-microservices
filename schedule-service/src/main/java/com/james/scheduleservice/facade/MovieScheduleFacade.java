package com.james.scheduleservice.facade;

import com.james.scheduleservice.dto.ScheduleDTO;

public interface MovieScheduleFacade {
  ScheduleDTO findScheduleById(Long id);
}
