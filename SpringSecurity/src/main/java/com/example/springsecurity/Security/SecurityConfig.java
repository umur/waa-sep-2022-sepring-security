package com.example.springsecurity.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/* This class is responsible for all the security configuration it provides information for spring about
* **** What type of Authentication Provider we are using
* **** What type of PassEncoder we are using
* **** Where our filters are located and
* **** it also tells spring which resources are granted to which user */

@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private JwtFilter jwtFilter;
    public SecurityConfig(JwtFilter jwtFilter){
        this.jwtFilter=jwtFilter;
    }


    @Bean
    public AuthenticationManager AuthManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService)throws  Exception{
       return http.getSharedObject(AuthenticationManagerBuilder.class)
               .userDetailsService(userDetailsService)
               .passwordEncoder(bCryptPasswordEncoder)
               .and()
               .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()

                //let in all request that come through "/uaa" route
                .antMatchers("/uaa/**").permitAll()
                .antMatchers("/products").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/categories").access("hasRole('ROLE_ADMIN')")

              //  .antMatchers("/**/**").access("hasRole('ADMIN')")

//                 .antMatchers(HttpMethod.GET,"/products").hasAuthority("GOLD")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            //use this filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder (){
        return new BCryptPasswordEncoder();
    }
}
