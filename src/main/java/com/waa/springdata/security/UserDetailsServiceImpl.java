package com.waa.springdata.security;

import com.waa.springdata.dto.UserInterfaceDto;
import com.waa.springdata.entity.User;
import com.waa.springdata.repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = this.userRepo.findByEmail(username);

       // create and return the UserDetails that Spring Security understands.
        // and to add extra fields, extend the spring UserDetails

        return new UserDetailsImpl(user) ;
    }

    public void registerUser(User user) {
        this.userRepo.save(user);
    }

    public List<User> getAllUser() {
        return StreamSupport
                .stream(this.userRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public List<UserInterfaceDto> getUsersSimplified() {
        var users = this.userRepo.getUsersSimplified();
        return users;
    }

    public UserInterfaceDto getUser(int id) {
        return userRepo.findByIdSimplified(id);
    }
}
