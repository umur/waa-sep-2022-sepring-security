package com.springsecuritydata1.springsecurity.repo;

import com.springsecuritydata1.springsecurity.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
