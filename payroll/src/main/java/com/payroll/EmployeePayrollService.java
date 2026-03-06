package com.payroll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {

    public List<EmployeePayrollData> readData() throws PayrollException {

        List<EmployeePayrollData> employeeList = new ArrayList<>();

        String query = "SELECT * FROM employee_payroll";

        try {

            PayrollDBService dbService = new PayrollDBService();
            Connection connection = dbService.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();

                employeeList.add(
                        new EmployeePayrollData(id, name, salary, startDate)
                );
            }

        } catch (SQLException e) {

            throw new PayrollException("Error retrieving payroll data", e);

        }

        return employeeList;
    }
}