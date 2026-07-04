package com.bank.menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.bank.entity.Account;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImpl;
import com.bank.util.ValidationUtil;
import com.bank.util.ExceptionUtil;

public class AccountMenu {

    private Scanner scanner;
    private AccountService accountService;

    public AccountMenu() {
        scanner = new Scanner(System.in);
        accountService = new AccountServiceImpl();
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("              ACCOUNT MANAGEMENT");
            System.out.println("==================================================");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. View All Accounts");
            System.out.println("4. Update Account");
            System.out.println("5. Delete Account");
            System.out.println("6. Balance Inquiry");
            System.out.println("7. Back to Main Menu");
            System.out.println("========================================");
            System.out.print("Enter your choice : ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    viewAccount();
                    break;

                case 3:
                    viewAllAccounts();
                    break;

                case 4:
                    updateAccount();
                    break;

                case 5:
                    deleteAccount();
                    break;

                case 6:
                    balanceInquiry();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void createAccount() {

        System.out.println("\n========== CREATE ACCOUNT ==========");

        Account account = new Account();

        try {

            System.out.print("Enter Customer ID : ");
            account.setCustomerId(Integer.parseInt(scanner.nextLine()));

            System.out.print("Enter Account Number : ");
            String accountNumber = scanner.nextLine();

            if (!ValidationUtil.isValidAccountNumber(accountNumber)) {
                System.out.println("Invalid Account Number.");
                return;
            }

            account.setAccountNumber(accountNumber);

            System.out.print("Enter Account Type (Savings/Current) : ");
            account.setAccountType(scanner.nextLine());

            System.out.print("Enter Opening Balance : ");
            BigDecimal balance = new BigDecimal(scanner.nextLine());

            if (!ValidationUtil.isValidAmount(balance)) {
                System.out.println("Invalid Amount.");
                return;
            }

            account.setBalance(balance);

            if (accountService.createAccount(account)) {
                System.out.println("\nAccount Created Successfully.");
            } else {
                System.out.println("\nFailed to Create Account.");
            }

        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }

    private void viewAccount() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            Account account = accountService.getAccountById(accountId);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.println("\nAccount ID      : " + account.getAccountId());
            System.out.println("Customer ID     : " + account.getCustomerId());
            System.out.println("Account Number  : " + account.getAccountNumber());
            System.out.println("Account Type    : " + account.getAccountType());
            System.out.println("Balance         : ₹" + account.getBalance());

        } catch (Exception e) {
            System.out.println("Invalid Account ID.");
        }
    }

    private void viewAllAccounts() {

        List<Account> accounts = accountService.getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("\nNo Accounts Found.");
            return;
        }

        System.out.println();

        System.out.printf("%-5s %-8s %-20s %-12s %-15s%n",
                "ID",
                "CustID",
                "Account Number",
                "Type",
                "Balance");

        System.out.println("-----------------------------------------------------------------------");

        for (Account account : accounts) {

            System.out.printf("%-5d %-8d %-20s %-12s ₹%-15s%n",
                    account.getAccountId(),
                    account.getCustomerId(),
                    account.getAccountNumber(),
                    account.getAccountType(),
                    account.getBalance());

        }
    }

    private void updateAccount() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            Account account = accountService.getAccountById(accountId);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter New Account Type : ");
            account.setAccountType(scanner.nextLine());

            System.out.print("Enter New Balance : ");
            BigDecimal balance = new BigDecimal(scanner.nextLine());

            if (!ValidationUtil.isValidAmount(balance)) {
                System.out.println("Invalid Amount.");
                return;
            }

            account.setBalance(balance);

            if (accountService.updateAccount(account)) {
                System.out.println("\nAccount Updated Successfully.");
            } else {
                System.out.println("\nUpdate Failed.");
            }

        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }

    private void deleteAccount() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            if (accountService.deleteAccount(accountId)) {
                System.out.println("\nAccount Deleted Successfully.");
            } else {
                System.out.println("\nAccount Not Found.");
            }

        } catch (Exception e) {
            System.out.println("Invalid Account ID.");
        }
    }

    private void balanceInquiry() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            Account account = accountService.getAccountById(accountId);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.println("\n========================================");
            System.out.println("BALANCE INQUIRY");
            System.out.println("========================================");
            System.out.println("Account Number : " + account.getAccountNumber());
            System.out.println("Account Type   : " + account.getAccountType());
            System.out.println("Balance        : ₹" + account.getBalance());
            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("Invalid Account ID.");
        }
    }
}