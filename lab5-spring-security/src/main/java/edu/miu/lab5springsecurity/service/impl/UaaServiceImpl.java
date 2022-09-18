package edu.miu.lab5springsecurity.service.impl;

import edu.miu.lab6springsecurity.dto.UserDto;
import edu.miu.lab6springsecurity.entity.User;
import edu.miu.lab6springsecurity.model.LoginRequest;
import edu.miu.lab6springsecurity.model.LoginResponse;
import edu.miu.lab6springsecurity.repository.RoleRepo;
import edu.miu.lab6springsecurity.repository.UserRepo;
import edu.miu.lab6springsecurity.security.JwtHelper;
import edu.miu.lab6springsecurity.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtHelper jwtHelper;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final RoleRepo roleRepo;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            var result = authenticationManager.authenticate(authenticationToken);
            final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
            var loginResponse = new LoginResponse(accessToken);
            return loginResponse;
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
        }
        return null;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto signup(UserDto userDto) {
        var user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList(roleRepo.findByRole("USER")));
        userRepo.save(user);
        return modelMapper.map(user, UserDto.class);
    }
}
