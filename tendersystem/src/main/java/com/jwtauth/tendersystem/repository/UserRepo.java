package com.jwtauth.tendersystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtauth.tendersystem.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);

    List<UserModel> findByEmail(String email);

}
