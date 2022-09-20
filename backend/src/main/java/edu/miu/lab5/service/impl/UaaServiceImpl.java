package edu.miu.lab5.service.impl;

import edu.miu.lab5.entity.Role;
import edu.miu.lab5.entity.User;
import edu.miu.lab5.model.LoginRequest;
import edu.miu.lab5.model.LoginResponse;
import edu.miu.lab5.model.RefreshTokenRequest;
import edu.miu.lab5.repo.UserRepo;
import edu.miu.lab5.security.JwtHelper;
import edu.miu.lab5.service.RoleService;
import edu.miu.lab5.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    private final JwtHelper jwtHelper;

    private final UserRepo userRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            log.error("Bad Credentials: ", e);
        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken, refreshToken, loginRequest.getEmail());
    }

    @Override
    public LoginResponse register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleService.getByName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepo.save(user);

        final String accessToken = jwtHelper.generateToken(user.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(user.getEmail());

        return new LoginResponse(accessToken, refreshToken, user.getEmail());
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());

        if (isRefreshTokenValid) {
            final String email = jwtHelper.getUsernameFromToken(refreshTokenRequest.getRefreshToken());
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshTokenRequest.getRefreshToken()));
            return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken(), email);
        }

        return new LoginResponse();
    }
}
