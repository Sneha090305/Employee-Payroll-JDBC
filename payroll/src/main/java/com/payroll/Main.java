package com.payroll;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        try {

            service.updateSalary("Bill", 2000000);

            List<EmployeePayrollData> employees = service.readData();

            for (EmployeePayrollData emp : employees) {
                System.out.println(emp);
            }

        } catch (PayrollException e) {

            System.out.println(e.getMessage());

        }
    }
}