package com.example.assignment5.security;

import com.example.assignment5.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final String password;
    private final String email;
    private final Long id;
    private final String username;
    private final boolean isEnabled;
    private final List<GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(User user){
        this.email = user.getEmail();
        this.id = user.getId();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.isEnabled = user.isEnabled();
        this.grantedAuthorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()))
                .collect(Collectors.toList());

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isEnabled;
    }
}
