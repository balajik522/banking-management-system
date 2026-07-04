package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.entity.Account;
import com.bank.util.DBConnection;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public boolean createAccount(Account account) {

        String sql = "INSERT INTO accounts (customer_id, account_number, account_type, balance) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, account.getCustomerId());
            preparedStatement.setString(2, account.getAccountNumber());
            preparedStatement.setString(3, account.getAccountType());
            preparedStatement.setBigDecimal(4, account.getBalance());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Account getAccountById(int accountId) {

        String sql = "SELECT * FROM accounts WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Account account = new Account();

                account.setAccountId(resultSet.getInt("account_id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setCreatedAt(resultSet.getTimestamp("created_at"));

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {

        String sql = "SELECT * FROM accounts WHERE account_number = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, accountNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Account account = new Account();

                account.setAccountId(resultSet.getInt("account_id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setCreatedAt(resultSet.getTimestamp("created_at"));

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();

        String sql = "SELECT * FROM accounts";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Account account = new Account();

                account.setAccountId(resultSet.getInt("account_id"));
                account.setCustomerId(resultSet.getInt("customer_id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setCreatedAt(resultSet.getTimestamp("created_at"));

                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public boolean updateAccount(Account account) {

        String sql = "UPDATE accounts SET customer_id = ?, account_number = ?, account_type = ?, balance = ? WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, account.getCustomerId());
            preparedStatement.setString(2, account.getAccountNumber());
            preparedStatement.setString(3, account.getAccountType());
            preparedStatement.setBigDecimal(4, account.getBalance());
            preparedStatement.setInt(5, account.getAccountId());

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteAccount(int accountId) {

        String sql = "DELETE FROM accounts WHERE account_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, accountId);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}