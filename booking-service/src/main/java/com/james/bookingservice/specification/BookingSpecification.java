package com.james.bookingservice.specification;

import com.james.bookingservice.entity.Booking;
import org.springframework.data.jpa.domain.Specification;

public class BookingSpecification {
  //    private Long theaterId;
  //    private Long aroundCreatedAt;

  public static Specification<Booking> hasTheaterId(Long theaterId) {
    return ((root, query, criteriaBuilder) -> {
      if (theaterId == null) return criteriaBuilder.conjunction();
      return criteriaBuilder.equal(root.get("theaterId"), theaterId);
    });
  }

  public static Specification<Booking> hasOwnerId(Long id) {
    return ((root, query, criteriaBuilder) -> {
      if (id == null) return criteriaBuilder.conjunction();
      return criteriaBuilder.equal(root.get("userId"), id);
    });
  }

  public static Specification<Booking> hasAroundCreatedAt(Long aroundCreatedAt) {
    return (root, query, criteriaBuilder) -> {
      if (aroundCreatedAt == null) {
        return criteriaBuilder.conjunction();
      }
      long rangeMillis = 30 * 60 * 1000L;
      long start = aroundCreatedAt - rangeMillis;
      long end = aroundCreatedAt + rangeMillis;

      return criteriaBuilder.between(root.get("createdAt"), start, end);
    };
  }
}
