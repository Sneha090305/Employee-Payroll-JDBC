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

    public List<EmployeePayrollData> getEmployeesByDateRange(String startDate, String endDate) throws PayrollException {

        List<EmployeePayrollData> employeeList = new ArrayList<>();

        String query = "SELECT * FROM employee_payroll WHERE start_date BETWEEN ? AND ?";

        try {

            PayrollDBService dbService = new PayrollDBService();
            Connection connection = dbService.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, startDate);
            statement.setString(2, endDate);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate start = resultSet.getDate("start_date").toLocalDate();

                employeeList.add(new EmployeePayrollData(id, name, salary, start));
            }

        } catch (SQLException e) {

            throw new PayrollException("Error retrieving employees by date range", e);

        }

        return employeeList;
    }

    public void getSalaryStatisticsByGender() throws PayrollException {

        String query = "SELECT gender, SUM(salary) AS total_salary, AVG(salary) AS avg_salary, " +
                "MIN(salary) AS min_salary, MAX(salary) AS max_salary, COUNT(*) AS count " +
                "FROM employee_payroll GROUP BY gender";

        try {

            PayrollDBService dbService = new PayrollDBService();
            Connection connection = dbService.getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String gender = resultSet.getString("gender");

                double sum = resultSet.getDouble("total_salary");
                double avg = resultSet.getDouble("avg_salary");
                double min = resultSet.getDouble("min_salary");
                double max = resultSet.getDouble("max_salary");
                int count = resultSet.getInt("count");

                System.out.println(
                        "Gender: " + gender +
                                ", SUM: " + sum +
                                ", AVG: " + avg +
                                ", MIN: " + min +
                                ", MAX: " + max +
                                ", COUNT: " + count
                );
            }

        } catch (SQLException e) {

            throw new PayrollException("Error retrieving salary statistics", e);

        }
    }
}