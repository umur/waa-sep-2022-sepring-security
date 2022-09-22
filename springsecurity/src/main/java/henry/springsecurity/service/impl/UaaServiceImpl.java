package henry.springsecurity.service.impl;

import henry.springsecurity.dto.UserDto;
import henry.springsecurity.entity.User;
import henry.springsecurity.model.LoginRequest;
import henry.springsecurity.model.LoginResponse;
import henry.springsecurity.model.RefreshTokenRequest;
import henry.springsecurity.repo.UserRepo;
import henry.springsecurity.security.JwtHelper;
import henry.springsecurity.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepo userRepo;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.info("Bad credentials");
        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse signup(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        final String accessToken = jwtHelper.generateToken(user.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(user.getEmail());
        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        final String refreshToken = refreshTokenRequest.getRefreshToken();
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshToken);
        if (isRefreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshToken));
            return new LoginResponse(accessToken, refreshToken);
        }

        return new LoginResponse();
    }
}
