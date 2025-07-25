package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long>, JpaSpecificationExecutor<Theater> {
    @Query("""
    SELECT t
    FROM Theater t
    WHERE t.id = :theaterId
        AND t.isActive = true
        AND t.directorId = :directorId
    """)
    Optional<Theater> findTheaterByDirectorIdAndTheaterId(Long directorId, Long theaterId);

}
