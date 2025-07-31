package com.james.scheduleservice.service;

import com.james.scheduleservice.resquest.NotificationNewTheaterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationService {

  @PostMapping(value = "/api/v1/notifications/new-theater", headers = "secret-key=schedule-service")
  void notificationNewTheater(@RequestBody NotificationNewTheaterRequest request);
}
