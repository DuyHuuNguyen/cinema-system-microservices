package com.james.userservice.specification;

import com.james.userservice.entity.User;
import com.james.userservice.entity.WorkPlace;
import com.james.userservice.entity.WorkShift;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class WorkShiftSpecification {
  public static Specification<WorkShift> hasOwnerId(Long id) {
    return (root, query, criteriaBuilder) -> {
      if (id == null) {
        return criteriaBuilder.conjunction();
      }
      Join<WorkShift, WorkPlace> joinWorkPlace = root.join("workPlace");
      Join<WorkPlace, User> joinUser = joinWorkPlace.join("employee");
      return criteriaBuilder.equal(joinUser.get("id"), id);
    };
  }

  public static Specification<WorkShift> hasTheaterId(Long id) {
    return (root, query, criteriaBuilder) -> {
      if (id == null) {
        return criteriaBuilder.conjunction();
      }
      Join<WorkShift, WorkPlace> joinWorkPlace = root.join("workPlace");
      return criteriaBuilder.equal(joinWorkPlace.get("theaterId"), id);
    };
  }
}
