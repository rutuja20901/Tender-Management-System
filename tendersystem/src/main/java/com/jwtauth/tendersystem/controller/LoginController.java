package com.jwtauth.tendersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.tendersystem.dto.LoginRequest;
import com.jwtauth.tendersystem.security.JwtUtil;
import com.jwtauth.tendersystem.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid Username or password");
        }

        UserDetails userDetails = loginService.loadUserByUsername(request.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }

}
