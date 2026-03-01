package com.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import io.github.cdimascio.dotenv.Dotenv;

public class PayrollDBService {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL =
            "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

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

    // update
    public int updateSalary(String name, double salary) {

        String query = String.format(
                "UPDATE employee_payroll SET salary = %.2f WHERE name = '%s'",
                salary, name);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            return statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new PayrollException("Error updating salary", e);
        }
    }

    // update using preparedStatement
    public int updateSalaryUsingPreparedStatement(String name, double salary) {

        String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setDouble(1, salary);
            pstmt.setString(2, name);

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new PayrollException("Error updating salary using PreparedStatement", e);
        }
    }

    public List<EmployeePayrollData> getEmployeesByDateRange(
            java.time.LocalDate start,
            java.time.LocalDate end) {

        List<EmployeePayrollData> list = new ArrayList<>();

        String query =
                "SELECT * FROM employee_payroll WHERE start_date BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(start));
            pstmt.setDate(2, Date.valueOf(end));

            ResultSet rs = pstmt.executeQuery();

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
            throw new PayrollException("Error retrieving by date range", e);
        }

        return list;
    }

    public void getSalaryStatisticsByGender() {

        String query =
                "SELECT gender, SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) " +
                        "FROM employee_payroll GROUP BY gender";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Gender: " + rs.getString(1));
                System.out.println("Sum: " + rs.getDouble(2));
                System.out.println("Avg: " + rs.getDouble(3));
                System.out.println("Min: " + rs.getDouble(4));
                System.out.println("Max: " + rs.getDouble(5));
                System.out.println("Count: " + rs.getInt(6));
                System.out.println("------------------");
            }

        } catch (SQLException e) {
            throw new PayrollException("Error getting statistics", e);
        }
    }
}