package com.springsecuritydata1.springsecurity.controller;

import com.springsecuritydata1.springsecurity.entity.User;
import com.springsecuritydata1.springsecurity.model.LoginRequest;
import com.springsecuritydata1.springsecurity.model.LoginResponse;
import com.springsecuritydata1.springsecurity.model.RefreshTokenRequest;
import com.springsecuritydata1.springsecurity.service.UaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/uaa")
public class UaaController {
    private final UaaService uaaService;

    public UaaController(UaaService uaaService){
        this.uaaService=uaaService;
    }
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse=uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody User user){
        User user1=uaaService.register(user);
           return ResponseEntity.ok().body(user1);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return uaaService.refreshToken(refreshTokenRequest);
    }


}
