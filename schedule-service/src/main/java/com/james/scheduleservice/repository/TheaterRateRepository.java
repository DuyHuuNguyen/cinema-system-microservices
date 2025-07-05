package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.TheaterRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRateRepository extends JpaRepository<TheaterRate, Long> {}
