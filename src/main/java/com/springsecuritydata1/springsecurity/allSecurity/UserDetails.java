package com.springsecuritydata1.springsecurity.allSecurity;

import com.springsecuritydata1.springsecurity.entity.Role;
import com.springsecuritydata1.springsecurity.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

   private String email;
   private String password;
   private List<Role> roles;

   public UserDetails(User user){
       this.email=user.getEmail();
       this.password=user.getPassword();
       this.roles=user.getRoles();
   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var result=roles.stream().map(role-> new SimpleGrantedAuthority(
                "ROLE_"+role.getRole().toUpperCase(Locale.ROOT))).collect(Collectors.toList());

        return result;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
