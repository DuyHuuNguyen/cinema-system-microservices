package com.james.scheduleservice.specification;

import com.james.scheduleservice.entity.MovieSchedule;
import com.james.scheduleservice.entity.Theater;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class MovieScheduleSpecification {

  public static Specification<MovieSchedule> hasMovieId(Long id) {
    return (root, query, criteriaBuilder) -> {
      if (id == null) {
        return criteriaBuilder.conjunction();
      }
      return criteriaBuilder.equal(root.get("movieId"), id);
    };
  }

  public static Specification<MovieSchedule> hasTheaterId(Long id) {
    return (root, query, criteriaBuilder) -> {
      if (id == null) {
        return criteriaBuilder.conjunction();
      }
      Join<MovieSchedule, Theater> movieScheduleTheaterJoin = root.join("theater");
      return criteriaBuilder.equal(movieScheduleTheaterJoin.get("id"), id);
    };
  }

  public static Specification<MovieSchedule> hasStartedAt(Long around) {
    return (root, query, criteriaBuilder) -> {
      if (around == null) {
        return criteriaBuilder.conjunction();
      }

      long thirtyMinutesInMillis = 30 * 60 * 1000;
      long lowerBound = around - thirtyMinutesInMillis;
      long upperBound = around + thirtyMinutesInMillis;

      return criteriaBuilder.between(root.get("startedAt"), lowerBound, upperBound);
    };
  }
}
