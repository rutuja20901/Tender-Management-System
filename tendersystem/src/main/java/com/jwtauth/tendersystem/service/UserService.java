package com.jwtauth.tendersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtauth.tendersystem.model.UserModel;
import com.jwtauth.tendersystem.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<UserModel> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
