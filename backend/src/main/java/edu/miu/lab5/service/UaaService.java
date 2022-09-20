package edu.miu.lab5.service;

import edu.miu.lab5.entity.User;
import edu.miu.lab5.model.LoginRequest;
import edu.miu.lab5.model.LoginResponse;
import edu.miu.lab5.model.RefreshTokenRequest;

public interface UaaService {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(User user);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
