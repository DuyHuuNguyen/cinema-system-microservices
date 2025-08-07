package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.service.ConsumerHandleScheduleService;
import com.james.scheduleservice.service.MovieScheduleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerHandleScheduleServiceImpl implements ConsumerHandleScheduleService {

  private final MovieScheduleService movieScheduleService;

  @Override
  @Transactional
  @RabbitListener(queues = "${rabbitmq.queue.movie-schedule-queue}")
  public void saveMovieScheduleMovie(List<MovieSchedule> movieSchedules) {
    log.info("Movie Schedule Save MovieSchedule");
    for (MovieSchedule movieSchedule : movieSchedules) {
      movieScheduleService.save(movieSchedule);
    }
  }
}
