package com.james.bookingservice.specification;

import com.james.bookingservice.entity.Voucher;
import org.springframework.data.jpa.domain.Specification;

public class VoucherSpecification {

  public static Specification<Voucher> hasTheaterId(Long id) {

    return ((root, query, criteriaBuilder) -> {
      if (id == null) return criteriaBuilder.conjunction();
      return criteriaBuilder.equal(root.get("theaterId"), id);
    });
  }
}
