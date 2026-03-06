package com.payroll;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        try {

            service.getEmployeePayrollDetails();

        } catch (PayrollException e) {

            System.out.println(e.getMessage());

        }
    }
}