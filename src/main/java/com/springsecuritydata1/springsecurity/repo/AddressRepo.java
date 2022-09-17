package com.springsecuritydata1.springsecurity.repo;

import com.springsecuritydata1.springsecurity.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
