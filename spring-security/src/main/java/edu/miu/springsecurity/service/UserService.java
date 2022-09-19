package edu.miu.springsecurity.service;

import edu.miu.springsecurity.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
