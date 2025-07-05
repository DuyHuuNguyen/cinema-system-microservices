package com.james.userservice.service;

import com.james.userservice.dto.ValidTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "identification-service")
public interface AuthService {
  @PostMapping("/api/v1/auth/authorization")
  ValidTokenDTO validToken(@RequestHeader("AccessToken") String accessToken);
}
