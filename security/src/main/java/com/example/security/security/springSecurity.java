//package com.example.security.security;
//
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//@Configuration
//@EnableWebSecurity
//public class springSecurity {
//
//    @Order(1)
//    @Configuration
//    public static class Security1 extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        MyUserDetailService userDetailService;
//
//       // @Autowired
//        //JwtUtilRequestFilter jwtUtilRequestFilter;
//
//
//
//        @Bean
//        public JwtUtilRequestFilter jwtUtilRequestFilter() {
//            return new JwtUtilRequestFilter();
//        }
//
//
//
//
////        @Autowired
////        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////            auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
////        }
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.cors()
//                    .and()
//                    .csrf()
//                    .disable()
//                    .authorizeRequests()
//                    .antMatchers("/authenticate").permitAll()
//                    .antMatchers("/").hasAuthority("ADMIN")
//                    .anyRequest().authenticated();
//
//                    http.addFilterBefore(jwtUtilRequestFilter(), UsernamePasswordAuthenticationFilter.class);
//                    //.formLogin();
//        }
////        @Bean
////        public PasswordEncoder passwordEncoder () {
////            return new BCryptPasswordEncoder();
////        }
////
////        @Bean
////        @Override
////        public AuthenticationManager authenticationManagerBean() throws Exception {
////            return super.authenticationManagerBean();
////        }
//
//
//    }
//    @Order(2)
//    @KeycloakConfiguration
////@EnableGlobalMethodSecurity(jsr250Enabled = true
//    @Import(KeycloakAuthenticationProvider.class)
//    public class OpenIDConfig extends KeycloakWebSecurityConfigurerAdapter
//    {
//        /**
//         * Registers the KeycloakAuthenticationProvider with the authentication manager.
//         */
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            KeycloakAuthenticationProvider authenticationProvider = new KeycloakAuthenticationProvider();
//            authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//            auth.authenticationProvider(authenticationProvider);
//        }
//
//        /**
//         * Defines the session authentication strategy.
//         */
//        @Bean
//        @Override
//        protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//            return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
//        }
//
//        @Bean
//        protected SessionRegistry buildSessionRegistry() {
//            return new SessionRegistryImpl();
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception
//        {
//            super.configure(http);
//            http
//                    .authorizeRequests()
//                    .antMatchers("/products/new").hasAuthority("user")
//                    .and()
//                    .httpBasic();
//        }
//    }
//}