package com.jwtauth.tendersystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String rolename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public RoleModel(int id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public RoleModel(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "RoleModel [id=" + id + ", rolename=" + rolename + "]";
    }

    public RoleModel() {

    }

    public RoleModel(int i) {
        // TODO Auto-generated constructor stub
    }

}
