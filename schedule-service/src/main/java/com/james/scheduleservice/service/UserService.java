package com.james.scheduleservice.service;

import com.james.scheduleservice.resquest.AddRoleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-service")
public interface UserService {
  @PatchMapping(value = "/api/v1/users/internal/{id}", headers = "secret-key=schedule-service")
  Boolean addRole(@PathVariable Long id, @RequestBody AddRoleRequest request);
}
