package com.james.userservice.repository;

import com.james.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT COUNT(u) = 0 FROM User u WHERE u.email = :email")
  boolean verify(@Param("email") String email);
}
