package com.springsecuritydata1.springsecurity.controller;


import com.springsecuritydata1.springsecurity.dto.UserDTO;
import com.springsecuritydata1.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Users")
public class UserController{

    private UserService userService;
    @PostMapping
    public UserDTO save(@RequestBody UserDTO userDTO){
        return userService.SaveUser(userDTO);
    }

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public  UserDTO find(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO,id);
    }

}
