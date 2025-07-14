package com.james.userservice.specification;

import com.james.userservice.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
  public static Specification<User> hasFirstname(String firstname) {
    return ((root, query, criteriaBuilder) -> {
      if (firstname.isBlank()) return criteriaBuilder.conjunction();
      return criteriaBuilder.like(root.get("firstName"), firstname);
    });
  }

  public static Specification<User> hasLastname(String lastname) {
    return ((root, query, criteriaBuilder) -> {
      if (lastname.isBlank()) return criteriaBuilder.conjunction();
      return criteriaBuilder.like(root.get("lastName"), lastname);
    });
  }

  public static Specification<User> hasEmail(String email) {
    return ((root, query, criteriaBuilder) -> {
      if (email.isBlank()) return criteriaBuilder.conjunction();
      return criteriaBuilder.like(root.get("email"), email);
    });
  }

  public static Specification<User> hasDateOfBirth(Long dateOfBirth) {
    return ((root, query, criteriaBuilder) -> {
      if (dateOfBirth == null) return criteriaBuilder.conjunction();
      return criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth);
    });
  }
}
