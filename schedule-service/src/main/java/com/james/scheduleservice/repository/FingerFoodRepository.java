package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.FingerFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FingerFoodRepository extends JpaRepository<FingerFood, Long> {}
