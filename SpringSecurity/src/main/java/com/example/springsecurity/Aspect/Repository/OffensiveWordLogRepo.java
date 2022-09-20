package com.example.springsecurity.Aspect.Repository;

import com.example.springsecurity.Aspect.Model.OffensiveWordLog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OffensiveWordLogRepo extends CrudRepository<OffensiveWordLog,Integer> {
    public List<OffensiveWordLog> findOffensiveWordLoggerByAppuseridAndTimestampMinuteBefore(int id, int min);
}
