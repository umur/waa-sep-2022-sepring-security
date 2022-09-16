package com.waa.springdata.config;

import com.waa.springdata.security.UserDetailsServiceImpl;
import com.waa.springdata.util.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfigurerAdapter is deprecated and a component-based
 * security configuration is preferred.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configuring HttpSecurity: - secure all http based requests, i.e others like https are not
     * HttpSecurity by creating a SecurityFilterChain bean.
     * Use Spring Security lambda DSL and the method HttpSecurity#authorizeHttpRequests
     * to define our authorization rules.
     *
     * @param http
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        http.csrf()
                .disable()
        .authorizeHttpRequests(auth ->
                auth.antMatchers("/index.html").permitAll()
                    .anyRequest()
                    .authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build()
                */
        http.cors().disable()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE)
                    .hasRole("ADMIN")
                .antMatchers("/admin/**")
                    .hasAnyRole("ADMIN")
                .antMatchers("/users/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/login/**")
                    .anonymous()
                .antMatchers("/uaa/**").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //.sessionAuthenticationFailureHandler();
        // prepend our jwtFilter to the httpSecurity
        // also specify the type of Authentication
        http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

    /**
     * Configuring WebSecurity - using the callback interface WebSecurityCustomizer
     * The WebSecurityCustomizer is a callback interface that can be used to customize WebSecurity
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // the next example line is redundant and can be done using
        // using permitAll via HttpSecurity#authorizeHttpRequests above.
        return (web) -> web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }

    /**
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Secure http requests
     * @param http
     * @param bCryptPasswordEncoder
     * @param userDetailsService
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                             BCryptPasswordEncoder bCryptPasswordEncoder,
                                             UserDetailsServiceImpl userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

}
