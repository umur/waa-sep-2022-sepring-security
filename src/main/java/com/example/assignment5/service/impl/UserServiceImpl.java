package com.example.assignment5.service.impl;

import com.example.assignment5.dto.UserDto;
import com.example.assignment5.entity.User;
import com.example.assignment5.mapper.UserMapper;
import com.example.assignment5.model.AuthenticationRequest;
import com.example.assignment5.model.AuthenticationResponse;
import com.example.assignment5.repository.UserRepo;
import com.example.assignment5.service.UserService;
import com.example.assignment5.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        String token = jwtUtil.generateJWT(authenticationRequest.getUsername());
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse signup(UserDto userDto){
        Optional<User> user = userRepo.findByEmailOrUsername(userDto.getEmail(), userDto.getUsername());
        if(user.isPresent()){
//            throw new UserFound
        }
        String password = passwordEncoder.encode(userDto.getPassword());
        User userEntity = userMapper.toEntity(userDto);
        userEntity.setPassword(password);
        userRepo.save(userEntity);
        String token = jwtUtil.generateJWT(userEntity.getUsername());
        return new AuthenticationResponse(token);

    }
}
