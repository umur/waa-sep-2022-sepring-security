package henry.springsecurity.service;

import henry.springsecurity.dto.UserDto;
import henry.springsecurity.model.LoginRequest;
import henry.springsecurity.model.LoginResponse;
import henry.springsecurity.model.RefreshTokenRequest;

public interface UaaService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse signup(UserDto userDto);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
