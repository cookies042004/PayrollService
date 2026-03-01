package com.bridgelabz;

import java.time.LocalDate;

public class PayrollMain {
    public static void main(String[] args) {
        PayrollDBService service = new PayrollDBService();
        service.getSalaryStatisticsByGender();
    }
}