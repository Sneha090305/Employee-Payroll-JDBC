package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayrollDBService {

    private static final String URL =
            "jdbc:mysql://localhost:3306/payroll_service";

    private static final String USER = "root";
    private static final String PASSWORD = "&Andy@1293";

    public Connection getConnection() throws PayrollException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connection Established Successfully");

            return connection;

        } catch (ClassNotFoundException | SQLException e) {

            throw new PayrollException("Database connection failed", e);

        }
    }
}