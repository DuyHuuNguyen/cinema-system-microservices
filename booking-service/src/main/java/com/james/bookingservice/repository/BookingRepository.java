package com.james.bookingservice.repository;

import com.james.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Modifying
    @Query(value = """
    UPDATE bookings
    SET payment_id =:paymentId
    WHERE id =:id
    """, nativeQuery = true)
    void addPaymentIdForBookingById(Long id,Long paymentId);
}
