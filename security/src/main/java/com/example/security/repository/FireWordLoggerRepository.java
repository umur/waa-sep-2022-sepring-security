package com.example.security.repository;

import com.example.security.entity.FireWordLogger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireWordLoggerRepository extends CrudRepository<FireWordLogger,Integer> {

    List<FireWordLogger> findAllByUserId(int id);
}
