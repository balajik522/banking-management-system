package com.bank.service;

import java.util.List;

import com.bank.dao.CustomerDAO;
import com.bank.dao.CustomerDAOImpl;
import com.bank.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        customerDAO = new CustomerDAOImpl();
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }
}