package com.james.scheduleservice.service;

import com.james.scheduleservice.dto.ValidTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "identification-service")
public interface AuthService {
  @PostMapping("/api/v1/auth/authorization")
  ValidTokenDTO validToken(@RequestHeader("AccessToken") String accessToken);
}
