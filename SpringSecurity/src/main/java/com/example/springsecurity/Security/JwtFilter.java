package com.example.springsecurity.Security;

import com.example.springsecurity.Security.Model.AppUserDetail;
import com.example.springsecurity.Security.Service.Impl.AppUserDetailSrevice;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtHelper jwtHelper;
    private UserDetailsService userDetailSrevice;
    public JwtFilter(JwtHelper jwtHelper, UserDetailsService userDetailSrevice){
        this.jwtHelper=jwtHelper;
        this.userDetailSrevice=userDetailSrevice;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String Authorization = request.getHeader("Authorization");
        if(Authorization!=null && Authorization.startsWith("Bearer ")){
            String token = Authorization.substring("Bearer ".length());
            boolean isTokenValid = jwtHelper.validateToken(token);
            if(isTokenValid){
                UserDetails userDetails = userDetailSrevice.loadUserByUsername(jwtHelper.getUsernameFromToken(token));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
