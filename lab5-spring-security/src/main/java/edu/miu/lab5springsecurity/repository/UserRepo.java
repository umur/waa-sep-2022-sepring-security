package edu.miu.lab5springsecurity.repository;

import edu.miu.lab6springsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
