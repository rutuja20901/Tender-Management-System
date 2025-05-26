package com.jwtauth.tendersystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtauth.tendersystem.model.BiddingModel;

@Repository
public interface BiddingRepo extends JpaRepository<BiddingModel, Integer> {

    Optional<BiddingModel> findByBidAmount(double bidAmount);

    boolean existsByProjectName(String projectName);

}
