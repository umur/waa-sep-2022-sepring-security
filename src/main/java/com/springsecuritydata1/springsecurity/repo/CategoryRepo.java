package com.springsecuritydata1.springsecurity.repo;

import com.springsecuritydata1.springsecurity.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
