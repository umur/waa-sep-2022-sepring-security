package edu.miu.service;

import edu.miu.model.LoginRequest;
import edu.miu.model.LoginResponse;
import edu.miu.model.RefreshTokenRequest;

public interface UaaService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
