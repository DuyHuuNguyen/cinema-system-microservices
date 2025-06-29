package com.james.movieservice.service.impl;

import com.james.movieservice.repository.CategoryRepository;
import com.james.movieservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;
}
