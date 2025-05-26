package com.jwtauth.tendersystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "Username")
    private String username;

    @Column(name = "Companyname")
    private String companyName;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Password")
    private String password;

    @OneToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private RoleModel role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public UserModel(Integer userId, String username, String companyName, String email, String password,
            RoleModel role) {
        this.userId = userId;
        this.username = username;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserModel(String username, String companyName, String email, String password, RoleModel role) {
        this.username = username;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserModel() {

    }

    @Override
    public String toString() {
        return "UserModel [userId=" + userId + ", username=" + username + ", companyName=" + companyName + ", email="
                + email + ", password=" + password + ", role=" + role + "]";
    }

}
