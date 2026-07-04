package com.bank.entity;

import java.sql.Timestamp;

public class Admin {

    private int adminId;
    private String username;
    private String password;
    private String fullName;
    private Timestamp createdAt;

    // Default Constructor
    public Admin() {
    }

    // Parameterized Constructor
    public Admin(int adminId, String username, String password,
                 String fullName, Timestamp createdAt) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}