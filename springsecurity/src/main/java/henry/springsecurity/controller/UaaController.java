package henry.springsecurity.controller;

import henry.springsecurity.dto.UserDto;
import henry.springsecurity.model.LoginRequest;
import henry.springsecurity.model.LoginResponse;
import henry.springsecurity.model.RefreshTokenRequest;
import henry.springsecurity.service.UaaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@CrossOrigin
@Slf4j
public class UaaController {
    private final UaaService uaaService;

    public UaaController(UaaService uaaService) {
        this.uaaService = uaaService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signup")
    public LoginResponse signup(@RequestBody UserDto userDto) {
        log.info("Controller: received signup");
        return uaaService.signup(userDto);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return uaaService.refreshToken(refreshTokenRequest);
    }
}
