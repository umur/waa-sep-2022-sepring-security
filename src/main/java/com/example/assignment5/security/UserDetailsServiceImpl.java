package com.example.assignment5.security;

import com.example.assignment5.entity.User;
import com.example.assignment5.repository.UserRepo;
import com.example.assignment5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepo.findByEmailOrUsername(username, username);
        return user
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("username "+username+ " not found"));
    }
}
