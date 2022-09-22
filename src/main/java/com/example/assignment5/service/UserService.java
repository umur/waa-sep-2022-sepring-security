package com.example.assignment5.service;

import com.example.assignment5.dto.UserDto;
import com.example.assignment5.entity.User;
import com.example.assignment5.model.AuthenticationRequest;
import com.example.assignment5.model.AuthenticationResponse;

import java.util.Optional;

public interface UserService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse signup(UserDto userDto);
}
