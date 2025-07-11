package com.james.userservice.service.impl;

import com.james.userservice.entity.WorkShift;
import com.james.userservice.repository.WorkShiftRepository;
import com.james.userservice.service.WorkShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkShiftServiceImpl implements WorkShiftService {
  private final WorkShiftRepository workShiftRepository;

  @Override
  public Page<WorkShift> findAll(Specification<WorkShift> specification, Pageable pageable) {
    return this.workShiftRepository.findAll(specification, pageable);
  }
}
