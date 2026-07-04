package com.bank.menu;

import java.util.List;
import java.util.Scanner;

import com.bank.entity.Customer;
import com.bank.service.CustomerService;
import com.bank.service.CustomerServiceImpl;
import com.bank.util.ValidationUtil;
import com.bank.util.ExceptionUtil;

public class CustomerMenu {

    private Scanner scanner;
    private CustomerService customerService;

    public CustomerMenu() {
        scanner = new Scanner(System.in);
        customerService = new CustomerServiceImpl();
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("             CUSTOMER MANAGEMENT");
            System.out.println("==================================================");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. View All Customers");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Back to Main Menu");
            System.out.println("========================================");
            ExceptionUtil.printError("Invalid choice.");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                ExceptionUtil.printError("Invalid choice.");
                continue;
            }

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    viewCustomer();
                    break;

                case 3:
                    viewAllCustomers();
                    break;

                case 4:
                    updateCustomer();
                    break;

                case 5:
                    deleteCustomer();
                    break;

                case 6:
                    return;

                default:
                    ExceptionUtil.printError("Invalid choice.");
            }
        }
    }

    private void addCustomer() {

        Customer customer = new Customer();

        ExceptionUtil.printError("========== ADD CUSTOMER ==========");

        ExceptionUtil.printError("Enter Full Name : ");
        String name = scanner.nextLine();

        if (!ValidationUtil.isValidName(name)) {
           ExceptionUtil.printError("Invalid Name.");
            return;
        }

        customer.setFullName(name);

        ExceptionUtil.printError("Enter Email : ");
        String email = scanner.nextLine();

        if (!ValidationUtil.isValidEmail(email)) {
            ExceptionUtil.printError("Invalid Email.");
            return;
        }

        customer.setEmail(email);

        ExceptionUtil.printError("Enter Phone Number : ");
        String phone = scanner.nextLine();

        if (!ValidationUtil.isValidPhone(phone)) {
            ExceptionUtil.printError("Invalid Phone Number.");
            return;
        }

        customer.setPhoneNumber(phone);

        ExceptionUtil.printError("Enter Address : ");
        customer.setAddress(scanner.nextLine());

        if (customerService.addCustomer(customer)) {
            ExceptionUtil.printSuccess("Customer Added Successfully.");
        } else {
            ExceptionUtil.printError("Failed to Add Customer.");
        }
    }

    private void viewCustomer() {

        ExceptionUtil.printError("Enter Customer ID : ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            ExceptionUtil.printError("Invalid Customer ID.");
            return;
        }

        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            ExceptionUtil.printError("Customer not found.");
            return;
        }

        System.out.println("\nCustomer ID : " + customer.getCustomerId());
        System.out.println("Name        : " + customer.getFullName());
        System.out.println("Email       : " + customer.getEmail());
        System.out.println("Phone       : " + customer.getPhoneNumber());
        System.out.println("Address     : " + customer.getAddress());
    }

    private void viewAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            ExceptionUtil.printError("\nNo Customers Found.");
            return;
        }

        System.out.println();

        System.out.printf("%-5s %-25s %-30s %-15s%n",
                "ID",
                "NAME",
                "EMAIL",
                "PHONE");
ExceptionUtil.printError("--------------------------------------------------------------------------");

        for (Customer customer : customers) {

            System.out.printf("%-5d %-25s %-30s %-15s%n",
                    customer.getCustomerId(),
                    customer.getFullName(),
                    customer.getEmail(),
                    customer.getPhoneNumber());

        }
    }

    private void updateCustomer() {

        ExceptionUtil.printError("\nEnter Customer ID : ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            ExceptionUtil.printError("Invalid Customer ID.");
            return;
        }

        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            ExceptionUtil.printError("Customer not found.");
            return;
        }

        System.out.print("Enter New Name : ");
        String name = scanner.nextLine();

        if (!ValidationUtil.isValidName(name)) {
            ExceptionUtil.printError("Invalid Name.");
            return;
        }

        customer.setFullName(name);

        System.out.print("Enter New Email : ");
        String email = scanner.nextLine();

        if (!ValidationUtil.isValidEmail(email)) {
            ExceptionUtil.printError("Invalid Email.");
            return;
        }

        customer.setEmail(email);

        System.out.print("Enter New Phone : ");
        String phone = scanner.nextLine();

        if (!ValidationUtil.isValidPhone(phone)) {
            ExceptionUtil.printError("Invalid Phone.");
            return;
        }

        customer.setPhoneNumber(phone);

        System.out.print("Enter New Address : ");
        customer.setAddress(scanner.nextLine());

        if (customerService.updateCustomer(customer)) {
            ExceptionUtil.printSuccess("Customer Updated Successfully.");
        } else {
            ExceptionUtil.printError("Failed to Update Customer.");
        }
    }

    private void deleteCustomer() {

        System.out.print("\nEnter Customer ID : ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            ExceptionUtil.printError("Invalid Customer ID.");
            return;
        }

        if (customerService.deleteCustomer(id)) {
            ExceptionUtil.printSuccess("Customer Deleted Successfully.");
        } else {
            ExceptionUtil.printError("Customer Not Found.");
        }
    }
}