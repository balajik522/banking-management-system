package com.bank.menu;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import com.bank.service.ReportService;
import com.bank.service.ReportServiceImpl;
import com.bank.util.ExceptionUtil;

public class ReportMenu {

    private Scanner scanner;
    private ReportService reportService;

    public ReportMenu() {
        scanner = new Scanner(System.in);
        reportService = new ReportServiceImpl();
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("           REPORTS MODULE");
            System.out.println("========================================");
            System.out.println("1. Total Customers");
            System.out.println("2. Total Accounts");
            System.out.println("3. Total Bank Balance");
            System.out.println("4. Customer Wise Accounts");
            System.out.println("5. Back to Main Menu");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {

                case 1:
                    showTotalCustomers();
                    break;

                case 2:
                    showTotalAccounts();
                    break;

                case 3:
                    showTotalBalance();
                    break;

                case 4:
                    showCustomerWiseAccounts();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void showTotalCustomers() {

        int total = reportService.getTotalCustomers();

        System.out.println("\n========================================");
        System.out.println("TOTAL CUSTOMERS");
        System.out.println("========================================");
        System.out.println("Total Customers : " + total);
        System.out.println("========================================");
    }

    private void showTotalAccounts() {

        int total = reportService.getTotalAccounts();

        System.out.println("\n========================================");
        System.out.println("TOTAL ACCOUNTS");
        System.out.println("========================================");
        System.out.println("Total Accounts : " + total);
        System.out.println("========================================");
    }

    private void showTotalBalance() {

        BigDecimal balance = reportService.getTotalBankBalance();

        System.out.println("\n========================================");
        System.out.println("TOTAL BANK BALANCE");
        System.out.println("========================================");
        System.out.println("Total Balance : ₹" + balance);
        System.out.println("========================================");
    }

    private void showCustomerWiseAccounts() {

        Map<String, Integer> report =
                reportService.getCustomerWiseAccounts();

        System.out.println("\n========================================");
        System.out.println("CUSTOMER WISE ACCOUNTS");
        System.out.println("========================================");

        System.out.printf("%-30s %-15s%n",
                "Customer Name",
                "Accounts");

        System.out.println("-----------------------------------------------");

        for (Map.Entry<String, Integer> entry : report.entrySet()) {

            System.out.printf("%-30s %-15d%n",
                    entry.getKey(),
                    entry.getValue());

        }

        System.out.println("========================================");
    }
}