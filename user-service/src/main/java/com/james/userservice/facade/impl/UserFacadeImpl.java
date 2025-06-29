package com.james.userservice.facade.impl;

import com.james.userservice.facade.UserFacade;
import com.james.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
  private final UserService userService;
}
