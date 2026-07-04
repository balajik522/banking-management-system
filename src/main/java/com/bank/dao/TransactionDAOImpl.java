package com.bank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bank.entity.Transaction;


import com.bank.util.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public boolean deposit(int accountId, BigDecimal amount, String description) {

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            String updateBalance =
                    "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(updateBalance)) {

                ps.setBigDecimal(1, amount);
                ps.setInt(2, accountId);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            String insertTransaction =
                    "INSERT INTO transactions(account_id,transaction_type,amount,description) VALUES(?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(insertTransaction)) {

                ps.setInt(1, accountId);
                ps.setString(2, "DEPOSIT");
                ps.setBigDecimal(3, amount);
                ps.setString(4, description);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (Exception e) {

            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean withdraw(int accountId, BigDecimal amount, String description) {

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            String updateBalance =
                    "UPDATE accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";

            try (PreparedStatement ps = connection.prepareStatement(updateBalance)) {

                ps.setBigDecimal(1, amount);
                ps.setInt(2, accountId);
                ps.setBigDecimal(3, amount);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            String insertTransaction =
                    "INSERT INTO transactions(account_id,transaction_type,amount,description) VALUES(?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(insertTransaction)) {

                ps.setInt(1, accountId);
                ps.setString(2, "WITHDRAW");
                ps.setBigDecimal(3, amount);
                ps.setString(4, description);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (Exception e) {

            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean transfer(int fromAccountId,
                            int toAccountId,
                            BigDecimal amount,
                            String description) {

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            String debitSql =
                    "UPDATE accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";

            try (PreparedStatement ps = connection.prepareStatement(debitSql)) {

                ps.setBigDecimal(1, amount);
                ps.setInt(2, fromAccountId);
                ps.setBigDecimal(3, amount);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            String creditSql =
                    "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(creditSql)) {

                ps.setBigDecimal(1, amount);
                ps.setInt(2, toAccountId);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            String insertSql =
                    "INSERT INTO transactions(account_id,transaction_type,amount,description) VALUES(?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(insertSql)) {

                ps.setInt(1, fromAccountId);
                ps.setString(2, "TRANSFER");
                ps.setBigDecimal(3, amount);
                ps.setString(4, "Transferred To Account ID : " + toAccountId);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }

                ps.setInt(1, toAccountId);
                ps.setString(2, "TRANSFER");
                ps.setBigDecimal(3, amount);
                ps.setString(4, "Received From Account ID : " + fromAccountId);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (Exception e) {

            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
@Override
public List<Transaction> getMiniStatement(int accountId) {

    List<Transaction> transactions = new ArrayList<>();

    String sql =
            "SELECT * FROM transactions WHERE account_id=? ORDER BY transaction_date DESC LIMIT 5";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setInt(1, accountId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Transaction transaction = new Transaction();

            transaction.setTransactionId(rs.getInt("transaction_id"));
            transaction.setAccountId(rs.getInt("account_id"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setAmount(rs.getBigDecimal("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

            transactions.add(transaction);

        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return transactions;

}
@Override
public List<Transaction> getTransactionHistory(int accountId) {

    List<Transaction> transactions = new ArrayList<>();

    String sql =
            "SELECT * FROM transactions WHERE account_id = ? ORDER BY transaction_date DESC";

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setInt(1, accountId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Transaction transaction = new Transaction();

            transaction.setTransactionId(rs.getInt("transaction_id"));
            transaction.setAccountId(rs.getInt("account_id"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setAmount(rs.getBigDecimal("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

            transactions.add(transaction);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return transactions;
}
}