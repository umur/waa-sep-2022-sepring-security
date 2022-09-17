package com.springsecuritydata1.springsecurity.service.impl;

import com.springsecuritydata1.springsecurity.allSecurity.JwtHelper;
import com.springsecuritydata1.springsecurity.entity.Role;
import com.springsecuritydata1.springsecurity.entity.User;
import com.springsecuritydata1.springsecurity.model.LoginRequest;
import com.springsecuritydata1.springsecurity.model.LoginResponse;
import com.springsecuritydata1.springsecurity.model.RefreshTokenRequest;
import com.springsecuritydata1.springsecurity.repo.RolesRepo;
import com.springsecuritydata1.springsecurity.repo.UserRepo;
import com.springsecuritydata1.springsecurity.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UaaServiceImpl implements UaaService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;
    private final UserRepo userRepo;
    private final RolesRepo rolesRepo;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try{
        var result = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
    }catch(BadCredentialsException emsg){
    log.info("Bad Credentials");

    }
    final String accessToken =jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        var loginResponse=new LoginResponse(accessToken,refreshToken);
        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject
                    (refreshTokenRequest.getRefreshToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            return loginResponse;
        }
        return new LoginResponse();
    }

    @Override
    public User register(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(rolesRepo.findById(2).get());
        user.setRoles(roles);
        return userRepo.save(user);
    }
}
