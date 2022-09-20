package com.example.springsecurity.Security.Service.Impl;

import com.example.springsecurity.Model.AppUser;
import com.example.springsecurity.Repository.AppUserRepo;
import com.example.springsecurity.Security.Model.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsService")
public class AppUserDetailSrevice implements UserDetailsService {
    @Autowired
    private AppUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user= userRepo.findAppUserByUsername(username);
        UserDetails userDetails= new AppUserDetail(user);
        System.out.println(userDetails.getAuthorities().toString());
        return userDetails;
    }
}
