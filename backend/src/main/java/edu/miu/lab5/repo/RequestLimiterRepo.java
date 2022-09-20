package edu.miu.lab5.repo;

import edu.miu.lab5.entity.RequestLimiter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLimiterRepo extends CrudRepository<RequestLimiter, Integer> {

    RequestLimiter findFirstByOrderByCreatedAtDesc();
}
