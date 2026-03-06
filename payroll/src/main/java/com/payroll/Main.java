package com.payroll;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        try {

            List<EmployeePayrollData> employees =
                    service.getEmployeesByDateRange("2018-01-01", "2025-12-31");

            for (EmployeePayrollData emp : employees) {

                System.out.println(emp);

            }

        } catch (PayrollException e) {

            System.out.println(e.getMessage());

        }
    }
}