package com.springsecuritydata1.springsecurity.repo;


import com.springsecuritydata1.springsecurity.entity.User;
import com.springsecuritydata1.springsecurity.entity.UserOffensiveCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffensiveWordRepo extends JpaRepository<UserOffensiveCount, Integer> {
    Optional<UserOffensiveCount> findByUser(User user);
}
