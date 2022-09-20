package edu.miu.repo;


import edu.miu.entity.Review;
import edu.miu.entity.Role;
import edu.miu.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByEmail(String email);

}
