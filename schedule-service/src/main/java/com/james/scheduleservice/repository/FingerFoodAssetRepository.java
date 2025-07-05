package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.FingerFoodAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FingerFoodAssetRepository extends JpaRepository<FingerFoodAsset, Long> {}
