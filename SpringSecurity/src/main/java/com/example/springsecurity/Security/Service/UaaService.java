package com.example.springsecurity.Security.Service;

import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.Security.Model.LoginRequest;
import com.example.springsecurity.Security.Model.LoginResponse;
import com.example.springsecurity.Security.Model.RefreshToken;

public interface UaaService {

    public LoginResponse login(LoginRequest loginRequest);
    public LoginResponse refreshToken(RefreshToken refreshToken);
    public AppUser signup(AppUser appUser);
}
