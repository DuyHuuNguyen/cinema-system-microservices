package com.james.movieservice.specification;

import com.james.movieservice.entity.Category;
import com.james.movieservice.entity.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {
  public static Specification<Movie> hasTitle(String title) {
    return (root, query, cb) ->
        title == null
            ? null
            : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
  }

  public static Specification<Movie> hasDescription(String description) {
    return (root, query, cb) ->
        description == null
            ? null
            : cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
  }

  public static Specification<Movie> hasCategory(String category) {
    return (root, query, cb) ->
        category == null
            ? null
            : cb.like(cb.lower(root.get("category")), "%" + category.toLowerCase() + "%");
  }

  public static Specification<Movie> hasDuration(String duration) {
    return (root, query, cb) ->
        duration == null
            ? null
            : cb.like(cb.lower(root.get("duration")), "%" + duration.toLowerCase() + "%");
  }

  public static Specification<Movie> hasReleaseAt(Long releaseAt) {
    return (root, query, cb) ->
        releaseAt == null ? null : cb.equal(root.get("releaseAt"), releaseAt);
  }

  public static Specification<Movie> hasTheaterId(Long theaterId) {
    return (root, query, cb) ->
        theaterId == null ? null : cb.equal(root.get("theaterId"), theaterId);
  }

  public static Specification<Movie> hasHobbies(List<String> hobbies) {
    return ((root, query, criteriaBuilder) -> {
      Join<Movie, Category> movieCategoryJoin = root.join("category");

      if (hobbies == null || hobbies.isEmpty()) {
        return criteriaBuilder.conjunction();
      }

      CriteriaBuilder.In<String> inClause =
          criteriaBuilder.in(movieCategoryJoin.get("categoryName"));
      for (String hobby : hobbies) {
        inClause.value(hobby);
      }
      return inClause;
    });
  }
}
