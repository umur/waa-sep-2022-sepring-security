package com.springsecuritydata1.springsecurity.service;

import com.springsecuritydata1.springsecurity.entity.User;
import com.springsecuritydata1.springsecurity.model.LoginRequest;
import com.springsecuritydata1.springsecurity.model.LoginResponse;
import com.springsecuritydata1.springsecurity.model.RefreshTokenRequest;

public interface UaaService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    User register(User user);
}
