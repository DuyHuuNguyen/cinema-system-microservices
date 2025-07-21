package com.james.movieservice.repository;

import com.james.movieservice.entity.MovieRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRateRepository
    extends JpaRepository<MovieRate, Long>, JpaSpecificationExecutor<MovieRate> {
    @Query(value = """
            SELECT AVG(r.star_number)
            FROM movie_rates r
            WHERE r.movie_id = :movieId
    """, nativeQuery = true)
    Double getAverageRatingByMovieId(@Param("movieId") Long movieId);

}
