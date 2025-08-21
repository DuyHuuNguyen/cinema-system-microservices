package com.james.scheduleservice.specification;

import com.james.scheduleservice.entity.FingerFood;
import com.james.scheduleservice.enums.FoodType;
import org.springframework.data.jpa.domain.Specification;

public class FoodSpecification {
  public static Specification<FingerFood> hasTheaterId(Long theaterId) {
    return (root, query, cb) -> {
      if (theaterId == null) return cb.conjunction();
      else return cb.equal(root.get("theater_id"), theaterId);
    };
  }

  public static Specification<FingerFood> hasPrice(Float price) {
    return (root, query, cb) -> {
      if (price == null) return cb.conjunction();
      return cb.lessThanOrEqualTo(root.get("price"), price);
    };
  }

  public static Specification<FingerFood> hasFoodType(FoodType foodType) {
    return (root, query, cb) -> {
      if (foodType == null) return cb.conjunction();
      return cb.equal(root.get("foodType"), foodType);
    };
  }
}
