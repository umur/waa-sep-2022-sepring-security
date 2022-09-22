package henry.springsecurity.repo;

import henry.springsecurity.entity.OffensiveWordsHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface OffensiveWordsHistoryRepo extends CrudRepository<OffensiveWordsHistory, Integer> {
    Set<OffensiveWordsHistory> findByAtLessThanEqualAndUser_Id(LocalDateTime duration, int userId);
}
