package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.service.ProducerHandleScheduleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerHandleScheduleServiceImpl implements ProducerHandleScheduleService {
  private RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.variable.movie-schedule-queue}")
  private String QUEUE_HANDLE_MOVIE_SCHEDULE;

  @Value("${rabbitmq.variable.movie-schedule-exchange}")
  private String EXCHANGE_HANDLE_MOVIE_SCHEDULE;

  @Value("${rabbitmq.variable.handle-movie-schedule-routing-key}")
  private String ROUTING_KEY_HANDLE_MOVIE_SCHEDULE;

  @Override
  public void saveMovieSchedule(List<MovieSchedule> movieSchedules) {
    log.info("send movie schedule to queue {}", QUEUE_HANDLE_MOVIE_SCHEDULE);
    rabbitTemplate.convertAndSend(
        EXCHANGE_HANDLE_MOVIE_SCHEDULE, ROUTING_KEY_HANDLE_MOVIE_SCHEDULE, movieSchedules);
  }
}
