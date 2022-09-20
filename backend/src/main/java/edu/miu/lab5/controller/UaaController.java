package edu.miu.lab5.controller;

import edu.miu.lab5.entity.User;
import edu.miu.lab5.model.LoginRequest;
import edu.miu.lab5.model.LoginResponse;
import edu.miu.lab5.model.RefreshTokenRequest;
import edu.miu.lab5.service.UaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uaa")
@RequiredArgsConstructor
public class UaaController {

    private final UaaService uaaService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return uaaService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public LoginResponse register(@RequestBody User user) {
        return uaaService.register(user);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return uaaService.refreshToken(refreshTokenRequest);
    }
}
