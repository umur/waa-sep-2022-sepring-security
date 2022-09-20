package com.example.springsecurity.Repository;


import com.example.springsecurity.Model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser,Integer> {
    public AppUser findAppUserByUsername(String username);
}
