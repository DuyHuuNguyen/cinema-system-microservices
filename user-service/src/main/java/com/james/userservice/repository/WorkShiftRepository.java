package com.james.userservice.repository;

import com.james.userservice.entity.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkShiftRepository
    extends JpaRepository<WorkShift, Long>, JpaSpecificationExecutor<WorkShift> {
    @Query("""
        SELECT ws
        FROM WorkShift ws
        WHERE ws.workPlace.employee = :ownerId AND ws.id = :id
    """)
    Optional<WorkShift> findByOwnerIdAndWorkShiftId(Long ownerId,Long id);

}
