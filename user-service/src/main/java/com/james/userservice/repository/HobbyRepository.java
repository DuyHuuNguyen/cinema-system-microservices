package com.james.userservice.repository;

import com.james.userservice.entity.Hobby;
import com.james.userservice.enums.HobbyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    @Query("""
    SELECT h
    FROM Hobby h
    WHERE h.hobbyName in :hobbies
    """)
    List<Hobby> findHobbiesByEnums( @Param("hobbies") List<HobbyEnum> hobbies);
}
