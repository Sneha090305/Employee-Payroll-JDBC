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

    public void updateSalary(String name, double salary) throws PayrollException {

        String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

        try {

            PayrollDBService dbService = new PayrollDBService();
            Connection connection = dbService.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setDouble(1, salary);
            statement.setString(2, name);

            int rowsUpdated = statement.executeUpdate();

            System.out.println("Rows Updated: " + rowsUpdated);

        } catch (SQLException e) {

            throw new PayrollException("Error updating employee salary", e);

        }
    }
}