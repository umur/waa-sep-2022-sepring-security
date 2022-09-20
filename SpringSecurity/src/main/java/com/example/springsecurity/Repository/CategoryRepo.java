package com.example.springsecurity.Repository;


import com.example.springsecurity.Model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepo extends CrudRepository<Category,Integer> {

}
