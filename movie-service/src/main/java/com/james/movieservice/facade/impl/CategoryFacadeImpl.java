package com.james.movieservice.facade.impl;

import com.james.movieservice.facade.CategoryFacade;
import com.james.movieservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFacadeImpl implements CategoryFacade {
  private final CategoryService categoryService;
}
