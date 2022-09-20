package edu.miu.lab5.repo;

import edu.miu.lab5.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {

    Role findByRole(String role);

}
