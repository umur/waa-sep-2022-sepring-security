package edu.miu.springsecurity.controller;



import edu.miu.springsecurity.dto.UserDto;
import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.model.LoginRequest;
import edu.miu.springsecurity.service.UaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@CrossOrigin
public class UaaController {

    @Autowired
    UaaService uaaService;

    @PostMapping
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        var loginResponse = uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signup")
    void signUp(@RequestBody UserDto user){
        uaaService.signUp(user);
    }

}
