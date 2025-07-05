package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.repository.FingerFoodRepository;
import com.james.scheduleservice.service.FingerFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FingerFoodServiceImpl implements FingerFoodService {
  private final FingerFoodRepository fingerFoodRepository;
}
