package com.jwtauth.tendersystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BiddingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private int biddingId;

    private String projectName = "Metro phsase V 2025";

    private Double bidAmount;

    private Double yearsToComplete;

    private String dateOfBidding;

    private String status = "pending";

    private int bidderId;

    public BiddingModel(int id, int biddingId, String projectName, double bidAmount, double yearsToComplete,
            String dateOfBidding, String status, int bidderId) {
        this.id = id;
        this.biddingId = biddingId;
        this.projectName = projectName;
        this.bidAmount = bidAmount;
        this.yearsToComplete = yearsToComplete;
        this.dateOfBidding = dateOfBidding;
        this.status = status;
        this.bidderId = bidderId;
    }

    public BiddingModel(int biddingId, String projectName, double bidAmount, double yearsToComplete,
            String dateOfBidding, String status, int bidderId) {
        this.biddingId = biddingId;
        this.projectName = projectName;
        this.bidAmount = bidAmount;
        this.yearsToComplete = yearsToComplete;
        this.dateOfBidding = dateOfBidding;
        this.status = status;
        this.bidderId = bidderId;
    }

    public BiddingModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(int biddingId) {
        this.biddingId = biddingId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getYearsToComplete() {
        return yearsToComplete;
    }

    public void setYearsToComplete(double yearsToComplete) {
        this.yearsToComplete = yearsToComplete;
    }

    public String getDateOfBidding() {
        return dateOfBidding;
    }

    public void setDateOfBidding(String dateOfBidding) {
        this.dateOfBidding = dateOfBidding;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    @Override
    public String toString() {
        return "BiddingModel [id=" + id + ", biddingId=" + biddingId + ", projectName=" + projectName + ", bidAmount="
                + bidAmount + ", yearsToComplete=" + yearsToComplete + ", dateOfBidding=" + dateOfBidding + ", status="
                + status + ", bidderId=" + bidderId + "]";
    }

    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
