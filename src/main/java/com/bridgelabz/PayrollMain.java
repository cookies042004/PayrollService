package com.bridgelabz;

import java.time.LocalDate;

public class PayrollMain {
    public static void main(String[] args) {
        PayrollDBService service = new PayrollDBService();

        service.getEmployeesByDateRange(
                        LocalDate.of(2018,1,1),
                        LocalDate.of(2021,1,1))
                .forEach(System.out::println);
    }
}