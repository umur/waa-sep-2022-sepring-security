package edu.miu.controller;

import edu.miu.model.LoginRequest;
import edu.miu.model.LoginResponse;
import edu.miu.model.RefreshTokenRequest;
import edu.miu.service.UaaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@CrossOrigin
public class UaaController {
    private final UaaService uaaService;

    public UaaController(UaaService uaaService) {
        this.uaaService = uaaService;
    }
    @PostMapping
    public ResponseEntity<?> loging (@RequestBody LoginRequest loginRequest) {
        var loginResponse = uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }
    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return uaaService.refreshToken(refreshTokenRequest);
    }
}
