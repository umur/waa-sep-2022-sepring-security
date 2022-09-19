package edu.miu.springsecurity.service.impl;

import edu.miu.springsecurity.dto.UserDto;
import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.model.LoginRequest;
import edu.miu.springsecurity.model.LoginResponse;
import edu.miu.springsecurity.repository.UserRepo;
import edu.miu.springsecurity.service.UaaService;
import edu.miu.springsecurity.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
//@Transactional
public class UaaServiceImpl implements UaaService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper mapper;

    @Autowired
    AuthenticationManager authenticationManager;
   @Autowired
    JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
            );
            return new LoginResponse(jwtUtil.generateToken(loginRequest.getEmail()),
                    jwtUtil.generateRefreshToken(loginRequest.getEmail()));

        }catch (BadCredentialsException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("Bad Credentials");
        }

    }

    @Override
    public void signUp(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        var user = mapper.map(userDto,User.class);
        userRepo.save(user);


    }
}
