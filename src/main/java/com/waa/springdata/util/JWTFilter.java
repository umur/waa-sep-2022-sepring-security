package com.waa.springdata.util;

import com.waa.springdata.security.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    UserDetailsServiceImpl userDetailsService;
    JwtHelper jwtHelper;

    public JWTFilter(UserDetailsServiceImpl userDetailsService, JwtHelper jwtHelper) {
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. Check if authorization header, token, exists
        final String authorizationHeader = request.getHeader("Authorization");
        // if we have a valid header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            // pick token which starts at index 7 i.e after 'Bearer '
            var token = authorizationHeader.substring(7);
            // Validate token
            boolean isTokenValid = jwtHelper.validateToken(token);
            // email is used as username in this application
            String userName = jwtHelper.getUsernameFromToken(token);
            // add User to securityContext holder if not already added
            if (isTokenValid && SecurityContextHolder.getContext().getAuthentication() == null) {
                // get user details
                var userDetails = userDetailsService.loadUserByUsername(userName);
                //We know that the credentials are username and password,
                // so, we use UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authentication = new
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // add the authentication to the security holder context
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        else {
           // response.sendRedirect("login.html");
        }
        // 2. Update the filter chain
        filterChain.doFilter(request,response);
    }
}
