package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.MovieSchedule;
import java.util.List;

public interface ConsumerHandleScheduleService {
  void saveMovieScheduleMovie(List<MovieSchedule> movieSchedules);
}
