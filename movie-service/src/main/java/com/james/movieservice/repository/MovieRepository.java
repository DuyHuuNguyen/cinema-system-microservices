package com.james.movieservice.repository;

import com.james.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository
    extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    @Query("""
        SELECT  m
        FROM Movie m
        WHERE m.id =:id
        AND m.theaterId =:theaterId
    """)
    Optional<Movie> findByTheaterIdAndMovieId(Long theaterId, Long id);
}
