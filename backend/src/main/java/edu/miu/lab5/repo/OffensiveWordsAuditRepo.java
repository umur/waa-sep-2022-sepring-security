package edu.miu.lab5.repo;

import edu.miu.lab5.entity.OffensiveWordAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface OffensiveWordsAuditRepo extends CrudRepository<OffensiveWordAudit, Integer> {

    Set<OffensiveWordAudit> findByCreatedAtLessThanEqualAndUserId(LocalDateTime last30Minutes, int userId);

}

