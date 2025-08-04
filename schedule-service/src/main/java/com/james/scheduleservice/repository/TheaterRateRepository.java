package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.TheaterRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRateRepository extends JpaRepository<TheaterRate, Long> {
    @Query("""
    SELECT AVG(tr.starNumber)
    FROM TheaterRate tr
    WHERE tr.theater.id = :id
    """)
    Double getAverageStarByTheaterId(Long id);
}
