package com.james.userservice.service;

import com.james.userservice.dto.InviteWatchingMovieDTO;
import com.james.userservice.dto.JobApplicationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationService {
  @PostMapping("/api/v1/notifications/job")
  void sendEmailApplyJob(@RequestBody JobApplicationDTO JobApplicationDTO);

  @PostMapping(value = "/api/v1/notifications/invitation")
  void sendEmailInviteWatchingMovie(@RequestBody InviteWatchingMovieDTO inviteWatchingMovieDTO);
}
