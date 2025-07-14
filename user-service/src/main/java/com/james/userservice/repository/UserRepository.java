package com.james.userservice.repository;

import com.james.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

  @Query("""
SELECT COUNT(u) = 0 FROM User u WHERE u.email = :email
""")
  boolean verify(@Param("email") String email);

  @Query("""
    SELECT u
    FROM User u
    WHERE u.id in :ids
""")
  List<User> findUserByIds(@Param("ids") List<Long> ids);
}
