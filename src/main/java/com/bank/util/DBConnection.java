package com.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/bank_management";

    // MySQL Username
    private static final String USERNAME = "root";

    // MySQL Password
    private static final String PASSWORD = "1223";

    // Method to get database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}