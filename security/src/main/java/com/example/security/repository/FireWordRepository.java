package com.example.security.repository;

import com.example.security.entity.FireWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireWordRepository extends CrudRepository<FireWord,Integer> {

    FireWord findByName(String name);
}
