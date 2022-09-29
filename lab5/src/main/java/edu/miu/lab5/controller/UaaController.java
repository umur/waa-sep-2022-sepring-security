package edu.miu.lab5.controller;

import edu.miu.lab5.model.LoginRequest;
import edu.miu.lab5.model.LoginResponse;
import edu.miu.lab5.model.RefreshTokenRequest;
import edu.miu.lab5.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/uaa")
@CrossOrigin
public class UaaController {
    private final IUserService userService;

    public UaaController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return userService.refreshToken(refreshTokenRequest);
    }

}
