package com.example.security.controller;

import com.example.security.entity.JwtResponse;
import com.example.security.entity.User;
import com.example.security.security.JwtUtil;
import com.example.security.security.MyUserDetailService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uaa")
public class UaaController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    UserService userService;

    @PostMapping ("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody User user){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);


        return ResponseEntity.ok().
                body(
                new JwtResponse(userDetails.getUsername(),token)
        );
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
