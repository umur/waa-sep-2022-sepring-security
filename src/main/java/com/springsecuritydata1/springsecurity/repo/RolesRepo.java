package com.springsecuritydata1.springsecurity.repo;

import com.springsecuritydata1.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo  extends JpaRepository<Role,Integer> {
}
