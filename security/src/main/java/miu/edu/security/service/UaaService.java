package miu.edu.security.service;

import miu.edu.security.model.LoginRequest;
import miu.edu.security.model.LoginResponse;
import miu.edu.security.model.RefreshTokenRequest;

public interface UaaService {
    LoginResponse login(LoginRequest loginRequest);
    //LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
