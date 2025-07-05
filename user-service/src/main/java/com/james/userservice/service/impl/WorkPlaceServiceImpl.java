package com.james.userservice.service.impl;

import com.james.userservice.service.WorkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkPlaceServiceImpl implements WorkPlaceService {
  private final WorkPlaceService workPlaceService;
}
