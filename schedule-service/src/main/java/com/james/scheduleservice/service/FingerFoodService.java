package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.FingerFood;
import java.util.Optional;

public interface FingerFoodService {
  Optional<FingerFood> findByIdAndTheaterId(Long foodId, Long theaterId);

  Optional<FingerFood> findById(Long id);
}
