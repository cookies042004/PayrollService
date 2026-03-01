package com.bridgelabz;

public class PayrollMain {
    public static void main(String[] args) {
        PayrollDBService service = new PayrollDBService();
        service.readData().forEach(System.out::println);
    }
}