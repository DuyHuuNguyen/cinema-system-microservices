package com.james.userservice.facade.impl;

import com.james.userservice.facade.WorkPlaceFacade;
import com.james.userservice.service.WorkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkPlaceFacadeImpl implements WorkPlaceFacade {
  private final WorkPlaceService workPlaceService;
}
