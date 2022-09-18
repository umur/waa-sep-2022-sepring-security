package com.example.security.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse  {

    private String jwtToken;

}
