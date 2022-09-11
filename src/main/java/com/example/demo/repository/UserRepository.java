package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByToken(String token);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT count(u.create_date) FROM users u\n" +
            "WHERE  to_char(u.create_date, 'MM/YYYY') = :time",nativeQuery = true)
    Long getUserByTime(@Param("time") String time);
}
