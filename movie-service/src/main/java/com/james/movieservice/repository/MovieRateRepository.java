package com.james.movieservice.repository;

import com.james.movieservice.entity.MovieRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRateRepository
    extends JpaRepository<MovieRate, Long>, JpaSpecificationExecutor<MovieRate> {}
