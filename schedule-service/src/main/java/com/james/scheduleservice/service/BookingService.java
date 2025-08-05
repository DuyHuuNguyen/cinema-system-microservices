package com.james.scheduleservice.service;

import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.resquest.CreateTicketInternalRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("booking-service")
public interface BookingService {
  @PostMapping(
      value = "/internal",
      headers = {"secret-key=schedule-service-23130075"})
  BaseResponse<Void> createTicketInternal(@RequestBody CreateTicketInternalRequest request);
}
