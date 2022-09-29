package edu.miu.lab5.service;

import edu.miu.lab5.model.LoginRequest;
import edu.miu.lab5.model.LoginResponse;
import edu.miu.lab5.model.RefreshTokenRequest;

public interface IUserService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
