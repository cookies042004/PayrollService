package com.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDBService {
    private static final String URL =
            "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public List<EmployeePayrollData> readData() {

        List<EmployeePayrollData> list = new ArrayList<>();
        String query = "SELECT * FROM employee_payroll";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                list.add(new EmployeePayrollData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getString("gender")
                ));
            }

        } catch (SQLException e) {
            throw new PayrollException("Error reading payroll data", e);
        }

        return list;
    }
}