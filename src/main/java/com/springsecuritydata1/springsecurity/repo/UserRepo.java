package com.springsecuritydata1.springsecurity.repo;

import com.springsecuritydata1.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmail(String email);
}
