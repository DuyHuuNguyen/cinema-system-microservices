package com.james.notificationservice.config;

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

  @Value("${rabbitmq-info.topic-exchange-user-notification}")
  private String TOPIC_EXCHANGE_USER_NOTIFICATION;

  @Value("${rabbitmq-info.routing-key-user-notification}")
  private String ROUTING_KEY_USER_NOTIFICATION;

  @Value("${rabbitmq-info.queue-name-user-notification}")
  private String QUEUE_USER_NOTIFICATION;

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public Queue queueUserNotification() {
    return new Queue(QUEUE_USER_NOTIFICATION);
  }

  @Bean
  public TopicExchange topicExchangeUserNotification() {
    return new TopicExchange(TOPIC_EXCHANGE_USER_NOTIFICATION);
  }

  @Bean
  public Binding bindingTopicExchangeToQueueUserNotification() {
    return BindingBuilder.bind(this.queueUserNotification())
        .to(topicExchangeUserNotification())
        .with(ROUTING_KEY_USER_NOTIFICATION);
  }
}
