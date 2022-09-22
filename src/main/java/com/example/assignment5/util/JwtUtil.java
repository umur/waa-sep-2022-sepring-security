package com.example.assignment5.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${app.jwt.token-expiration-length}")
    private Long jwtExpirationLength;

    @Value("${app.jwt.secret-key}")
    private String jwtSecretKey;

    public String generateJWT(String subject){
        return createJWT(new HashMap<>(), subject);
    }

    public String generateJWT(String subject, Map<String, Object> claims){
        return createJWT(claims, subject);
    }

    public String getTokenSubject(String token){
        return getJWTBody(token).getSubject();
    }

    public boolean isValidToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException | SignatureException | MalformedJwtException | IllegalArgumentException | UnsupportedJwtException e){
            System.out.println("Invalid Token");
        }catch (Exception e){
            System.out.println("Something went wrong");
            throw e;
        }
        return false;
    }

    private String createJWT(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * jwtExpirationLength))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }

    private Claims getJWTBody(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException | SignatureException | MalformedJwtException | IllegalArgumentException | UnsupportedJwtException e){
            System.out.println("Invalid Token");
            throw e;
        }catch (Exception e){
            System.out.println("Something went wrong");
            throw e;
        }
    }
}
