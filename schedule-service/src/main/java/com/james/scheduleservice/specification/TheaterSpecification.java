package com.james.scheduleservice.specification;

import com.james.scheduleservice.dto.LocationCriteriaDTO;
import com.james.scheduleservice.entity.Location;
import com.james.scheduleservice.entity.Theater;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class TheaterSpecification {
  public static Specification<Theater> hasTheaterName(String name) {
    return ((root, query, criteriaBuilder) ->
        name == null
            ? criteriaBuilder.conjunction()
            : criteriaBuilder.like(
                criteriaBuilder.lower(root.get("theaterName")), "%" + name.toLowerCase() + "%"));
  }

  public static Specification<Theater> hasLocation(LocationCriteriaDTO locationCriteriaDTO) {

    return (root, query, criteriaBuilder) -> {
      if (locationCriteriaDTO == null) return criteriaBuilder.conjunction();

      double lat = locationCriteriaDTO.getLatitude();
      double lon = locationCriteriaDTO.getLongitude();
      double radiusInKm = locationCriteriaDTO.getRadiusInKm();

      Join<Theater, Location> locationJoin = root.join("location", JoinType.INNER);

      Expression<Double> latExpr = locationJoin.get("latitude");
      Expression<Double> lonExpr = locationJoin.get("longitude");

      Expression<Double> distance =
          criteriaBuilder.function(
              "6371 * acos(cos(radians(?1)) * cos(radians("
                  + "latitude)) * cos(radians(longitude) - radians(?2)) + "
                  + "sin(radians(?1)) * sin(radians(latitude)))",
              Double.class,
              criteriaBuilder.literal(lat),
              criteriaBuilder.literal(lon));

      return criteriaBuilder.lessThanOrEqualTo(distance, radiusInKm);
    };
  }
}
