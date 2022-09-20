package com.example.springsecurity.Security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;


/*====================
* This class is resposible to generate and manage jwts
* **** Generate token,
* **** Generate Refresh Token
* **** Get Username from token
* **** Get subject
* in order to work with jwts it requires the io.jsonwebtoken dependency */
@Component
public class JwtHelper {
    String secret = "mySecrete";
    private int Expiration = 2*24*60*60*1000;


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .compact();

    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setIssuedAt(new Date(System.currentTimeMillis() + Expiration))
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;

        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (ExpiredJwtException e) {
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
        return   Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
