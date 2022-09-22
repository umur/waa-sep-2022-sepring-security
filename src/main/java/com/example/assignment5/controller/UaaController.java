package com.example.assignment5.controller;

import com.example.assignment5.dto.UserDto;
import com.example.assignment5.model.AuthenticationRequest;
import com.example.assignment5.model.AuthenticationResponse;
import com.example.assignment5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@RequiredArgsConstructor
public class UaaController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return userService.authenticate(authenticationRequest);
    }

    @PostMapping("/signup")
    public AuthenticationResponse signup(@RequestBody UserDto userDto){
        return userService.signup(userDto);
    }
}
