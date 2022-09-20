package com.example.springsecurity.Security.Service.Impl;

import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.Repository.AppUserRepo;
import com.example.springsecurity.Security.JwtHelper;
import com.example.springsecurity.Security.Model.LoginRequest;
import com.example.springsecurity.Security.Model.LoginResponse;
import com.example.springsecurity.Security.Model.RefreshToken;
import com.example.springsecurity.Security.Service.UaaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UaaServiceImpl implements UaaService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            String accesstoken = jwtHelper.generateToken(loginRequest.getUsername());
            String refreshtoken = jwtHelper.generateRefreshToken(loginRequest.getUsername());
            return new LoginResponse(accesstoken,refreshtoken);
        }catch (BadCredentialsException e){
            System.out.println(e.getMessage());
            throw new BadCredentialsException("Bad Credentials");
        }


    }

    @Override
    public AppUser signup(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }


    @Override
    public LoginResponse refreshToken(RefreshToken refreshToken) {
        boolean isvalid = jwtHelper.validateToken(refreshToken.getRefreshToken());
        String accesstoken=jwtHelper.generateToken(jwtHelper.getUsernameFromToken(refreshToken.getRefreshToken()));

        return new LoginResponse(accesstoken,refreshToken.getRefreshToken());
    }


}
