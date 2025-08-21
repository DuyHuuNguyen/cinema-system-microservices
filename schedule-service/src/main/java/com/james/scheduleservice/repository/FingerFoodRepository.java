package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.FingerFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FingerFoodRepository extends JpaRepository<FingerFood, Long> , JpaSpecificationExecutor<FingerFood> {
    @Query("""
    SELECT f
    FROM FingerFood f
    WHERE f.theater.id =:theaterId AND f.id  =:id
    """)
    Optional<FingerFood> findFingerFoodByIdAndTheaterId(Long id, Long theaterId);
}
