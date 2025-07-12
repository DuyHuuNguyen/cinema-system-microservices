package com.james.userservice.service;

import com.james.userservice.entity.WorkShift;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface WorkShiftService {
  Page<WorkShift> findAll(Specification<WorkShift> shiftSpecification, Pageable pageable);

  Optional<WorkShift> findWorkShiftByOwnerIdAndId(Long ownerId, Long id);

  void save(WorkShift workShift);
}
