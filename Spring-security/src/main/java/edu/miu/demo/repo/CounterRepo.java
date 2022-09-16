package edu.miu.demo.repo;

import edu.miu.demo.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CounterRepo extends JpaRepository<Counter, Long> {
    List<Counter> findAllByUserId(Long id);

    List<Counter> findAllByUserIdAndCreatedAtBetween(Long id, Instant date1, Instant date2);
}