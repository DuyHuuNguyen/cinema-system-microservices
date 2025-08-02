package com.james.scheduleservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${rabbitmq.variable.movie-schedule-queue}")
  private String QUEUE_HANDLE_MOVIE_SCHEDULE;

  @Value("${rabbitmq.variable.movie-schedule-exchange}")
  private String EXCHANGE_HANDLE_MOVIE_SCHEDULE;

  @Value("${rabbitmq.variable.handle-movie-schedule-routing-key}")
  private String ROUTING_KEY_HANDLE_MOVIE_SCHEDULE;

  @Bean
  public Queue scheduleQueue() {
    return new Queue(QUEUE_HANDLE_MOVIE_SCHEDULE);
  }

  @Bean
  public TopicExchange scheduleExchange() {
    return new TopicExchange(EXCHANGE_HANDLE_MOVIE_SCHEDULE);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public Binding userMailBinding() {
    return BindingBuilder.bind(scheduleQueue())
        .to(scheduleExchange())
        .with(ROUTING_KEY_HANDLE_MOVIE_SCHEDULE);
  }
}
