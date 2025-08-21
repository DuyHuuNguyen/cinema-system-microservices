package com.james.scheduleservice.service.impl;

import com.james.scheduleservice.entity.FingerFood;
import com.james.scheduleservice.repository.FingerFoodRepository;
import com.james.scheduleservice.service.FingerFoodService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FingerFoodServiceImpl implements FingerFoodService {
  private final FingerFoodRepository fingerFoodRepository;

  @Override
  public Optional<FingerFood> findByIdAndTheaterId(Long foodId, Long theaterId) {
    return this.fingerFoodRepository.findFingerFoodByIdAndTheaterId(foodId, theaterId);
  }

  @Override
  public Optional<FingerFood> findById(Long id) {
    return this.fingerFoodRepository.findById(id);
  }
}
