package com.james.identificationservice.facade.Iml;

import com.james.identificationservice.facade.UserFacade;
import com.james.identificationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeIml implements UserFacade {
  private final UserService userService;
}
