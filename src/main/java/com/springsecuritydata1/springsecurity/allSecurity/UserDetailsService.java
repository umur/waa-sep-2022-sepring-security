package com.springsecuritydata1.springsecurity.allSecurity;

import com.springsecuritydata1.springsecurity.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user=userRepo.findByEmail(username);
        var userDetails=new com.springsecuritydata1.springsecurity.allSecurity.UserDetails(user);
        return userDetails;
    }
}
