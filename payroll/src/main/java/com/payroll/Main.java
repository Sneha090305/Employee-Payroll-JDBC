package com.payroll;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        try {

            service.addEmployee("John", 3000000, "2024-01-01");

        } catch (PayrollException e) {

            System.out.println(e.getMessage());

        }
    }
}