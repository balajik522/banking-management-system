package com.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.bank.dao.TransactionDAO;
import com.bank.dao.TransactionDAOImpl;
import com.bank.entity.Transaction;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionDAO transactionDAO;

    public TransactionServiceImpl() {
        transactionDAO = new TransactionDAOImpl();
    }

    @Override
    public boolean deposit(int accountId,
                           BigDecimal amount,
                           String description) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return transactionDAO.deposit(accountId, amount, description);
    }

    @Override
    public boolean withdraw(int accountId,
                            BigDecimal amount,
                            String description) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return transactionDAO.withdraw(accountId, amount, description);
    }

    @Override
    public boolean transfer(int fromAccountId,
                            int toAccountId,
                            BigDecimal amount,
                            String description) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (fromAccountId == toAccountId) {
            return false;
        }

        return transactionDAO.transfer(
                fromAccountId,
                toAccountId,
                amount,
                description);
    }

    @Override
    public List<Transaction> getMiniStatement(int accountId) {
        return transactionDAO.getMiniStatement(accountId);
    }

    @Override
    public List<Transaction> getTransactionHistory(int accountId) {
        return transactionDAO.getTransactionHistory(accountId);
    }
}