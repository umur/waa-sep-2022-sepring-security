package com.example.springsecurity.Repository;

import com.example.springsecurity.Model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends CrudRepository<Review,Integer> {
}
