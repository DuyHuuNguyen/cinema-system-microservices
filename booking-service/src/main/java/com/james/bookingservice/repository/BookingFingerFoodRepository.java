package com.james.bookingservice.repository;

import com.james.bookingservice.entity.BookingFingerFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingFingerFoodRepository extends JpaRepository<BookingFingerFood, Long> {}
