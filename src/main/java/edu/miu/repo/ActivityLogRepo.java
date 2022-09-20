package edu.miu.repo;

import edu.miu.entity.ActivityLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ActivityLogRepo extends CrudRepository<ActivityLog, Integer> {

}
