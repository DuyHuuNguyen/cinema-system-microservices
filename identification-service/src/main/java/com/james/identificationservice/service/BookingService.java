package com.james.identificationservice.service;

import com.james.identificationservice.config.feign.FeignClientConfig;
import com.james.identificationservice.request.AuthenticationRequest;
import com.james.identificationservice.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "booking-service", configuration = FeignClientConfig.class)
public interface BookingService {
  @PostMapping("/api/v1/auth")
  BaseResponse<Void> addAuthentication(@RequestBody AuthenticationRequest request);
}
