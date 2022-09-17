package com.springsecuritydata1.springsecurity.repo;

import com.springsecuritydata1.springsecurity.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review,Integer> {
}
