package com.jwtauth.tendersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtauth.tendersystem.model.RoleModel;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Integer> {

}
