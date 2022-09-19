package edu.miu.springsecurity.repository;

import edu.miu.springsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findAll();
}
