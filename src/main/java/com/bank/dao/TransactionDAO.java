package com.bank.dao;

import java.math.BigDecimal;
import java.util.List;

import com.bank.entity.Transaction;

public interface TransactionDAO {

    boolean deposit(int accountId,
                    BigDecimal amount,
                    String description);

    boolean withdraw(int accountId,
                     BigDecimal amount,
                     String description);

    boolean transfer(int fromAccountId,
                     int toAccountId,
                     BigDecimal amount,
                     String description);

    List<Transaction> getMiniStatement(int accountId);

    List<Transaction> getTransactionHistory(int accountId);

}