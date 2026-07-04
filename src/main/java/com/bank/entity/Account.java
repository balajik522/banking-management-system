package com.bank.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Account {

    private int accountId;
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private int customerId;
    private Timestamp createdAt;

    // Default Constructor
    public Account() {
    }

    // Parameterized Constructor
    public Account(int accountId, String accountNumber, String accountType,
                   BigDecimal balance, int customerId, Timestamp createdAt) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}