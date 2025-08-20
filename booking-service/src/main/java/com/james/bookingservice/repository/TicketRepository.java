package com.james.bookingservice.repository;

import com.james.bookingservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("""
    SELECT t 
    FROM Ticket t
    WHERE t.scheduleId =:scheduleId
    """)
    List<Ticket> findTicketsByScheduleId(Long scheduleId);
}
