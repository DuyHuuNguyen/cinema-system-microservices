package com.james.scheduleservice.repository;

import com.james.scheduleservice.entity.MovieSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, Long> , JpaSpecificationExecutor<MovieSchedule> {

    @Query(value = """
        SELECT count(*) > 0
        FROM movie_schedules s
        WHERE s.theater_id =:theaterId
          AND s.room_id =:roomId
          AND tsrange(
                to_timestamp(s.started_at / 1000)::timestamp,
                to_timestamp(s.finished_at / 1000)::timestamp
              )
          && tsrange(
                to_timestamp(:startedAt / 1000)::timestamp,
                to_timestamp(:finishedAt / 1000)::timestamp
              )
    """, nativeQuery = true)
    Boolean validMovieSchedule(Long theaterId, Long roomId, Long startedAt, Long finishedAt);

    @Query(value = """
    SELECT *
    FROM movie_schedules s
    WHERE s.theater_id =:theaterId
      AND s.room_id =:roomId
      and to_timestamp(s.started_at / 1000)::date =  to_timestamp(:created_at / 1000)::date;
    """,nativeQuery = true)
    List<MovieSchedule> findMovieSchedulesByDateAndTheaterIdAndRoomId(@Param("created_at") Long createdAt, Long roomId, Long theaterId);
}
