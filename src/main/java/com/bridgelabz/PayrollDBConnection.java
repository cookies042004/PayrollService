package com.bridgelabz;

import java.sql.*;
import java.util.Enumeration;

public class PayrollDBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root";
    private static final String PASSWORD = "DB_PASSWORD";

    public static void main(String[] args) {
        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");

            // 2. List Registered Drivers
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                System.out.println("Driver: " + drivers.nextElement());
            }

            // 3. Get Connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Established!");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}