package com.springsecuritydata1.springsecurity.allSecurity;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;

@Component
public class JwtHelper {
    private final String secretWord = "top-secret-word";

    private final long expiredOn = 15 * 60 * 60 * 60;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredOn))
                .signWith(SignatureAlgorithm.HS512, secretWord)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expiredOn))
                .signWith(SignatureAlgorithm.HS512, secretWord)
                .compact();
    }
    public String getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secretWord)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                    .setSigningKey(secretWord)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException emsg) {
            System.out.println(emsg.getMessage());

        } catch (MalformedJwtException emsg) {
            System.out.println(emsg.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
public String doGenerateRefreshToken(Map<String, Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+expiredOn))
                .signWith(SignatureAlgorithm.HS512,secretWord).compact();
}
public String getUsernameFromToken(String token){
        String result = null;
        try{
            return Jwts.parser()
                    .setSigningKey(secretWord)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

        }catch (ExpiredJwtException emsg){
            System.out.println(emsg.getMessage());
            throw emsg;
        }
        catch (Exception emsg){
            System.out.println(emsg.getMessage());
        }
        return result;
}
}