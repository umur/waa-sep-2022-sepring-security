package miu.edu.lab05.repository;

import miu.edu.lab05.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {
    List<Counter> findAllByUserId(Long id);

    List<Counter> findAllByUserIdAndCreatedAtBetween(Long id, Instant date1, Instant date2);
}
