package henry.springsecurity.repo;

import henry.springsecurity.entity.RequestLimit;
import henry.springsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface RequestLimitRepo extends CrudRepository<RequestLimit, Integer> {
    RequestLimit findFirstByUser_IdOrderByAtDesc(int userId);
}
