package com.james.paymentservice.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("booking-service")
public interface BookingService {}
