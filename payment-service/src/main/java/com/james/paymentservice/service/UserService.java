package com.james.paymentservice.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface UserService {}
