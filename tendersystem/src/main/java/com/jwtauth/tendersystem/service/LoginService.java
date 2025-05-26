package com.jwtauth.tendersystem.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.jwtauth.tendersystem.model.UserModel;
import com.jwtauth.tendersystem.repository.UserRepo;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // Load user by username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in username: " + username));
        List<GrantedAuthority> authorities = buildUserForAuthority(user.getRole().getRolename());
        return buildUserForAuthentication(user, authorities);
    }

    // build authorities list for user
    public List<GrantedAuthority> buildUserForAuthority(String userRole) {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + userRole.toUpperCase()));
    }

    // build UserDetails object for spring security
    public UserDetails buildUserForAuthentication(UserModel user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
