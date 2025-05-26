package com.jwtauth.tendersystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jwtauth.tendersystem.model.BiddingModel;
import com.jwtauth.tendersystem.repository.BiddingRepo;

@Service
public class BiddingService {

    @Autowired
    private BiddingRepo biddingRepo;

    // add Bidding entry
    public ResponseEntity<Object> addBidding(BiddingModel bidding) {
        if (biddingRepo.existsByProjectName(bidding.getProjectName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            BiddingModel b = new BiddingModel(bidding.getBiddingId(), bidding.getProjectName(), bidding.getBidAmount(),
                    bidding.getYearsToComplete(), bidding.getDateOfBidding(), bidding.getStatus(),
                    bidding.getBidderId());
            biddingRepo.save(b);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    // List of bidding by bid amount
    public ResponseEntity<Object> getById() {
        List<BiddingModel> b = biddingRepo.findAll();
        if (b.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(b);
        }
    }

    // List of bidding by bid amount
    public ResponseEntity<Object> getBidding(double bidAmount) {
        Optional<BiddingModel> b = biddingRepo.findByBidAmount(bidAmount);
        if (b.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(b);
        }
    }

    // Update by id
    public ResponseEntity<Object> updateBidding(BiddingModel bidding, int id) {
        Optional<BiddingModel> bid = biddingRepo.findById(id);
        if (bid.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            BiddingModel b = bid.get();
            b.setStatus(bidding.getStatus());
            BiddingModel a = biddingRepo.save(b);
            return ResponseEntity.status(HttpStatus.OK).body(a);

        }

    }

    // Delete bidding by id
    public ResponseEntity<Object> deleteBidding(int id) {
        Optional<BiddingModel> bid = biddingRepo.findById(id);
        if (bid.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        BiddingModel bidding = bid.get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        boolean isApprover = auth.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_APPROVER"));
        boolean isCreator = bidding.getCreatedBy().equals(currentUser);

        if (!isApprover && !isCreator) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete!");
        }

        biddingRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
