package com.bank.dao;

import java.util.List;

import com.bank.entity.Account;

public interface AccountDAO {

    boolean createAccount(Account account);

    Account getAccountById(int accountId);

    Account getAccountByNumber(String accountNumber);

    List<Account> getAllAccounts();

    boolean updateAccount(Account account);

    boolean deleteAccount(int accountId);
}