package com.james.bookingservice.repository;

import com.james.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> , JpaSpecificationExecutor<Booking> {

    @Modifying
    @Query(value = """
    UPDATE bookings
    SET payment_id =:paymentId
    WHERE id =:id
    """, nativeQuery = true)
    void addPaymentIdForBookingById(Long id,Long paymentId);

    @Query("""
    SELECT b
    FROM Booking b
    WHERE b.id =:id AND b.userId =:ownerId
    """)
    Optional<Booking> findByIdAndOwnerId(Long id, Long ownerId);
}
