package com.bank.service;

import java.util.List;

import com.bank.dao.AccountDAO;
import com.bank.dao.AccountDAOImpl;
import com.bank.entity.Account;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
    }

    @Override
    public boolean createAccount(Account account) {
        return accountDAO.createAccount(account);
    }

    @Override
    public Account getAccountById(int accountId) {
        return accountDAO.getAccountById(accountId);
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountDAO.getAccountByNumber(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountDAO.updateAccount(account);
    }

    @Override
    public boolean deleteAccount(int accountId) {
        return accountDAO.deleteAccount(accountId);
    }
}