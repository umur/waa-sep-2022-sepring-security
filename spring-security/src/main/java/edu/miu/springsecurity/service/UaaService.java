package edu.miu.springsecurity.service;

import edu.miu.springsecurity.dto.UserDto;
import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.model.LoginRequest;
import edu.miu.springsecurity.model.LoginResponse;


public interface UaaService {
    LoginResponse login(LoginRequest loginRequest);

    void signUp(UserDto user);
}
