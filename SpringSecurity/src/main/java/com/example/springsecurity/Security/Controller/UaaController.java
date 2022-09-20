package com.example.springsecurity.Security.Controller;

import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.Security.Model.LoginRequest;
import com.example.springsecurity.Security.Model.LoginResponse;
import com.example.springsecurity.Security.Model.RefreshToken;
import com.example.springsecurity.Security.Service.UaaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/uaa")
public class UaaController {
    private UaaService uaaService;

    public UaaController(UaaService uaaService){
        this.uaaService=uaaService;
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        var loginResponse =  uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }


    @PostMapping("/signup")
    public ResponseEntity<AppUser> signup(@RequestBody AppUser user){
        return ResponseEntity.ok().body(uaaService.signup(user)) ;
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestBody RefreshToken refreshToken){
        return uaaService.refreshToken(refreshToken);
    }

}
