package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.MovieSchedule;
import java.util.List;

public interface ProducerHandleScheduleService {
  void saveMovieSchedule(List<MovieSchedule> movieSchedules);
}
