package com.bank.menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImpl;
import com.bank.service.TransactionService;
import com.bank.service.TransactionServiceImpl;
import com.bank.util.ValidationUtil;
import com.bank.util.ExceptionUtil;

public class TransactionMenu {

    private Scanner scanner;
    private TransactionService transactionService;
    private AccountService accountService;

    public TransactionMenu() {
        scanner = new Scanner(System.in);
        transactionService = new TransactionServiceImpl();
        accountService = new AccountServiceImpl();
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("            TRANSACTION MANAGEMENT");
            System.out.println("==================================================");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Transaction History");
            System.out.println("6. Back to Main Menu");
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
                    depositMoney();
                    break;

                case 2:
                    withdrawMoney();
                    break;

                case 3:
                    transferMoney();
                    break;

                case 4:
                    miniStatement();
                    break;

                case 5:
                    transactionHistory();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void depositMoney() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            Account account = accountService.getAccountById(accountId);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.println("Current Balance : ₹" + account.getBalance());

            System.out.print("Enter Deposit Amount : ");
            BigDecimal amount = new BigDecimal(scanner.nextLine());

            if (!ValidationUtil.isValidAmount(amount)) {
                System.out.println("Invalid Amount.");
                return;
            }

            System.out.print("Enter Description : ");
            String description = scanner.nextLine();

            if (transactionService.deposit(accountId, amount, description)) {

                Account updated = accountService.getAccountById(accountId);

                System.out.println("\nDeposit Successful.");
                System.out.println("Updated Balance : ₹" + updated.getBalance());

            } else {

                System.out.println("Deposit Failed.");

            }

        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }

    private void withdrawMoney() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            Account account = accountService.getAccountById(accountId);

            if (account == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.println("Current Balance : ₹" + account.getBalance());

            System.out.print("Enter Withdraw Amount : ");
            BigDecimal amount = new BigDecimal(scanner.nextLine());

            if (!ValidationUtil.isValidAmount(amount)) {
                System.out.println("Invalid Amount.");
                return;
            }

            System.out.print("Enter Description : ");
            String description = scanner.nextLine();

            if (transactionService.withdraw(accountId, amount, description)) {

                Account updated = accountService.getAccountById(accountId);

                System.out.println("\nWithdraw Successful.");
                System.out.println("Updated Balance : ₹" + updated.getBalance());

            } else {

                System.out.println("Withdraw Failed.");

            }

        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }

    private void transferMoney() {

        try {

            System.out.print("\nFrom Account ID : ");
            int fromAccount = Integer.parseInt(scanner.nextLine());

            System.out.print("To Account ID : ");
            int toAccount = Integer.parseInt(scanner.nextLine());

            System.out.print("Transfer Amount : ");
            BigDecimal amount = new BigDecimal(scanner.nextLine());

            if (!ValidationUtil.isValidAmount(amount)) {
                System.out.println("Invalid Amount.");
                return;
            }

            System.out.print("Description : ");
            String description = scanner.nextLine();

            if (transactionService.transfer(fromAccount, toAccount, amount, description)) {

                System.out.println("\nTransfer Successful.");

            } else {

                System.out.println("\nTransfer Failed.");

            }

        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }

    private void miniStatement() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            List<Transaction> transactions =
                    transactionService.getMiniStatement(accountId);

            if (transactions.isEmpty()) {
                System.out.println("No Transactions Found.");
                return;
            }

            System.out.println("\n============= MINI STATEMENT =============");

            for (Transaction transaction : transactions) {

                System.out.println("-----------------------------------------");
                System.out.println("Type        : " + transaction.getTransactionType());
                System.out.println("Amount      : ₹" + transaction.getAmount());
                System.out.println("Description : " + transaction.getDescription());
                System.out.println("Date        : " + transaction.getTransactionDate());

            }

        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }

    private void transactionHistory() {

        try {

            System.out.print("\nEnter Account ID : ");
            int accountId = Integer.parseInt(scanner.nextLine());

            List<Transaction> transactions =
                    transactionService.getTransactionHistory(accountId);

            if (transactions.isEmpty()) {
                System.out.println("No Transactions Found.");
                return;
            }

            System.out.println("\n=========== TRANSACTION HISTORY ==========");

            for (Transaction transaction : transactions) {

                System.out.println("-----------------------------------------");
                System.out.println("Type        : " + transaction.getTransactionType());
                System.out.println("Amount      : ₹" + transaction.getAmount());
                System.out.println("Description : " + transaction.getDescription());
                System.out.println("Date        : " + transaction.getTransactionDate());

            }

        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }
}