package com.james.bookingservice.config;

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

  @Value("${rabbitmq.variable.handle-ticket-queue}")
  private String HANDLE_TICKET_QUEUE;

  @Value("${rabbitmq.variable.handle-ticket-exchange}")
  private String HANDLE_TICKET_EXCHANGE;

  @Value("${rabbitmq.variable.handle-ticket-routing-key}")
  private String HANDLE_TICKET_ROUTING_KEY;

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public Queue handleTicketQueue() {
    return new Queue(HANDLE_TICKET_QUEUE);
  }

  @Bean
  public TopicExchange handleTicketExchange() {
    return new TopicExchange(HANDLE_TICKET_EXCHANGE);
  }

  @Bean
  public Binding handleTicketBinding() {
    return BindingBuilder.bind(handleTicketQueue())
        .to(handleTicketExchange())
        .with(HANDLE_TICKET_ROUTING_KEY);
  }
}
