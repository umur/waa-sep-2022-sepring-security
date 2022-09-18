package edu.miu.lab5springsecurity.controller;

import edu.miu.lab6springsecurity.dto.UserDto;
import edu.miu.lab6springsecurity.model.LoginRequest;
import edu.miu.lab6springsecurity.service.UaaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/uaa")
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
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(uaaService.signup(userDto));
    }
}
