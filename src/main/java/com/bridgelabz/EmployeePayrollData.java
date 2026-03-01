package com.bridgelabz;

import java.time.LocalDate;

public class EmployeePayrollData {
    private int id;
    private String name;
    private double salary;
    private LocalDate startDate;
    private String gender;

    public EmployeePayrollData(int id, String name, double salary,
                               LocalDate startDate, String gender) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.startDate = startDate;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "EmployeePayrollData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", gender='" + gender + '\'' +
                '}';
    }
}