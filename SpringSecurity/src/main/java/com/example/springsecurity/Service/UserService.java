package com.example.springsecurity.Service;



import com.example.springsecurity.Model.AppUser;

import java.util.List;

public interface UserService {
    public List<AppUser> findAllUsers();
    public AppUser findUserByID();
    public void addUser();
    public void updateUser();
    public void deleteUser();
}
