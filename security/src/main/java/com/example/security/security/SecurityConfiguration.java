package com.example.security.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,
        jsr250Enabled = true,
        securedEnabled = true
)
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

   @Autowired
   JwtUtilRequestFilter jwtUtilRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/uaa/**").permitAll()
                .antMatchers("/products/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/**").hasAuthority("ADMIN")
                //.antMatchers("/products/*").hasAuthority("USER")
                .anyRequest()
                .authenticated()
                //.antMatchers("/products").hasAuthority("USER")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtUtilRequestFilter, UsernamePasswordAuthenticationFilter.class);
                //.formLogin();
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
