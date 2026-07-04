package com.bank.service;

import java.util.List;

import com.bank.entity.Customer;

public interface CustomerService {

    boolean addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(int customerId);
}