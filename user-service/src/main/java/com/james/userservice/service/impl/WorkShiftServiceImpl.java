package com.james.userservice.service.impl;

import com.james.userservice.repository.WorkShiftRepository;
import com.james.userservice.service.WorkShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkShiftServiceImpl implements WorkShiftService {
  private final WorkShiftRepository workShiftRepository;
}
