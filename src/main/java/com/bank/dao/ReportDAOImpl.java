package com.bank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.bank.util.DBConnection;

public class ReportDAOImpl implements ReportDAO {

    @Override
    public int getTotalCustomers() {

        String sql = "SELECT COUNT(*) FROM customers";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getTotalAccounts() {

        String sql = "SELECT COUNT(*) FROM accounts";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public BigDecimal getTotalBankBalance() {

        String sql = "SELECT IFNULL(SUM(balance),0) FROM accounts";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getBigDecimal(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }

    @Override
    public Map<String, Integer> getCustomerWiseAccounts() {

        Map<String, Integer> report = new LinkedHashMap<>();

        String sql =
                "SELECT c.full_name, COUNT(a.account_id) AS total_accounts " +
                "FROM customers c " +
                "LEFT JOIN accounts a ON c.customer_id = a.customer_id " +
                "GROUP BY c.customer_id, c.full_name " +
                "ORDER BY c.full_name";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                report.put(
                        rs.getString("full_name"),
                        rs.getInt("total_accounts")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }
}