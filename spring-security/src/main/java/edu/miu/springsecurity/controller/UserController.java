package edu.miu.springsecurity.controller;

import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    List<User> findAll(){
        return userService.findAll();
    }
}
