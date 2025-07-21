package com.james.movieservice.service;

import com.james.movieservice.entity.Category;
import java.util.Optional;

public interface CategoryService {
  Optional<Category> findById(Long id);
}
