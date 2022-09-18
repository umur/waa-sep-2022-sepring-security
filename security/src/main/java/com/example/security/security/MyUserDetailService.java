package com.example.security.security;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    public MyUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        user.orElseThrow();

        return new MyUserDetail(user.get());
    }
}
