package com.james.scheduleservice.specification;

import com.james.scheduleservice.entity.Room;
import com.james.scheduleservice.entity.Theater;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class RoomSpecification {

  public static Specification<Room> hasTheaterId(Long id) {
    return ((root, query, criteriaBuilder) -> {
      if (id == null) criteriaBuilder.conjunction();

      Join<Room, Theater> roomTheaterJoin = root.join("theater");
      return criteriaBuilder.equal(roomTheaterJoin.get("id"), id);
    });
  }
}
