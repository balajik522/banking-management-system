package com.bank.menu;

import java.math.BigDecimal;
import java.util.Scanner;

import com.bank.service.ReportService;
import com.bank.service.ReportServiceImpl;

public class MainMenu {

    private Scanner scanner;
    private ReportService reportService;

    public MainMenu() {
        scanner = new Scanner(System.in);
        reportService = new ReportServiceImpl();
    }

    public void start() {

    while (true) {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("            BANKING MANAGEMENT SYSTEM");
        System.out.println("==================================================");
        System.out.println(" 1. Customer Management");
        System.out.println(" 2. Account Management");
        System.out.println(" 3. Transaction Management");
        System.out.println(" 4. Reports");
        System.out.println(" 5. Exit");
        System.out.println("==================================================");
        System.out.print("Enter your choice : ");

        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input! Please enter a valid number.");
            continue;
        }

        switch (choice) {

            case 1:
                new CustomerMenu().start();
                break;

            case 2:
                new AccountMenu().start();
                break;

            case 3:
                new TransactionMenu().start();
                break;

            case 4:
                new ReportMenu().start();
                break;

            case 5:
                System.out.println();
                System.out.println("==========================================");
                System.out.println("Thank you for using Banking Management System.");
                System.out.println("Have a Great Day!");
                System.out.println("==========================================");
                System.exit(0);
                break;

            default:
                System.out.println("\nInvalid choice! Please try again.");
        }
    }
}
}