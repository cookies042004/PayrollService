package com.bridgelabz;

public class PayrollMain {
    public static void main(String[] args) {
        PayrollDBService service = new PayrollDBService();
        service.updateSalary("Terisa", 3000000.00);
        service.readData().forEach(System.out::println);
    }
}