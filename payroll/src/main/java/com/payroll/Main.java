package com.payroll;

public class Main {

    public static void main(String[] args) {

        PayrollDBService dbService = new PayrollDBService();

        try {

            dbService.getConnection();

        } catch (PayrollException e) {

            System.out.println(e.getMessage());

        }
    }
}