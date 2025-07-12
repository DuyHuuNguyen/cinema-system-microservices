package com.james.userservice.service;

import com.james.userservice.dto.InviteWatchingMovieDTO;
import com.james.userservice.dto.JobApplicationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationService {
  @PostMapping(value = "/api/v1/notifications/job", headers = "secret-key=user-service001")
  void sendEmailApplyJob(@RequestBody JobApplicationDTO JobApplicationDTO);

  @PostMapping(value = "/api/v1/notifications/invitation", headers = "secret-key=user-service001")
  void sendEmailInviteWatchingMovie(@RequestBody InviteWatchingMovieDTO inviteWatchingMovieDTO);
}
