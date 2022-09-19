package edu.miu.springsecurity.service.impl;

import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.repository.UserRepo;
import edu.miu.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;


    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
