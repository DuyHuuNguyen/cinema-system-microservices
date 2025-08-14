package com.james.paymentservice.reporitory;

import com.james.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Modifying
    @Query(value = """
    UPDATE payments p
    SET p.booking_id =:bookingId
    WHERE p.id =:id
    """,nativeQuery = true)
    void addBookingIdForPayment(Long id, Long bookingId);
}
