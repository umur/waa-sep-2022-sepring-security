package miu.edu.lab3.Repo;

import miu.edu.lab3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   // public User save(User user);
  User findByEmail(String username);
}
