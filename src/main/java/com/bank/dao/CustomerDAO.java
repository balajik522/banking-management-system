package com.bank.dao;

import java.util.List;

import com.bank.entity.Customer;

public interface CustomerDAO {

    boolean addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(int customerId);
}