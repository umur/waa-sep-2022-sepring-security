package edu.miu.controller;//package edu.miu.lab_3.controller;


import edu.miu.dto.UserDto;
import edu.miu.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping()
    public List<UserDto> findAll() {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable int id){
        return userService.findUserById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
    }
    @PostMapping
    public void saveUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

}
