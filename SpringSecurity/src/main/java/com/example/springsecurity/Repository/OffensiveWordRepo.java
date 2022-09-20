package com.example.springsecurity.Repository;

import com.example.springsecurity.Model.OffensiveWord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OffensiveWordRepo extends CrudRepository<OffensiveWord, Integer> {

}
