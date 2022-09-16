package com.waa.springdata.repo;

import com.waa.springdata.dto.UserInterfaceDto;
import com.waa.springdata.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository <User, Integer> {
    User findByEmail(String email);
    @Query("select u.id as id, u.firstName as firstName, u.lastName as lastName, " +
            "u.email as email, u.roles as role, u.address.street as street, " +
            "u.address.city as city, u.address.zip as zip from User u " +
            "where u.id = :id")
    UserInterfaceDto findByIdSimplified(int id);
    @Query("select u.id as id, u.firstName as firstName, u.lastName as lastName, " +
            "u.email as email, u.roles as role, u.address.street as street, " +
            "u.address.city as city, u.address.zip as zip from User u")
    List<UserInterfaceDto> getUsersSimplified();
}
