package com.jwtauth.tendersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.tendersystem.model.BiddingModel;
import com.jwtauth.tendersystem.service.BiddingService;

@RestController
@RequestMapping("/tenders/bidding")
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    // to create bidding using biddingmodel object
    @PostMapping("/add")
    public ResponseEntity<Object> postBidding(@RequestBody BiddingModel bid) {
        return biddingService.addBidding(bid);
    }

    // getting bidding details by bidamount
    @GetMapping("/list")
    public ResponseEntity<Object> getBidding(@RequestParam double bidAmount) {
        return biddingService.getBidding(bidAmount);
    }

    // getting bidding details by id
    @GetMapping("/get")
    public ResponseEntity<Object> getById() {
        return biddingService.getById();
    }

    // update bidding details by status
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBidding(@RequestBody BiddingModel bid, @PathVariable int id) {
        return biddingService.updateBidding(bid, id);
    }

    // delet bidding by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBidding(@PathVariable int id) {
        return biddingService.deleteBidding(id);
    }

}
