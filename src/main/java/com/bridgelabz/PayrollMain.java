package com.bridgelabz;

public class PayrollMain {
    public static void main(String[] args) {
        PayrollDBService service = new PayrollDBService();
        service.updateSalaryUsingPreparedStatement("Terisa", 3500000.00);
        service.readData().forEach(System.out::println);
    }
}