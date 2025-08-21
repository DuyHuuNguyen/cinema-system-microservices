package com.james.scheduleservice.service;

import com.james.scheduleservice.entity.FingerFood;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface FingerFoodService {
  Optional<FingerFood> findByIdAndTheaterId(Long foodId, Long theaterId);

  Optional<FingerFood> findById(Long id);

  Page<FingerFood> findAll(Specification<FingerFood> specification, Pageable pageable);
}
