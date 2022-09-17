package com.example.springsecurity.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@RequiredArgsConstructor
public class JwtHelper {
        private final String secret ="mysecrete";
        private final long expiration =5 * 60 * 60 * 60;
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public String generateRefreshToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean validateToken(String token){
       try {
           Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token);
           return true;
       }
       catch(Exception e){
           System.out.println(e.getMessage());
        }
       return false;
    }

    public String getSubject(String token){
      return   Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getUsernameFromToken(String token){
      return  Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
