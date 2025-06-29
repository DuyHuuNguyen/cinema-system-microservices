package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.TheaterRateAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRateAssetRepository extends JpaRepository<TheaterRateAsset, Long> {}
