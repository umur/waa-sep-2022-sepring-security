package edu.miu.lab5springsecurity.repository;

import edu.miu.lab6springsecurity.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    public Role findByRole(String role);
}
