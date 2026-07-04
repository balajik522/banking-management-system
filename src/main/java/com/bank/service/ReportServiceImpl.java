package com.bank.service;

import java.math.BigDecimal;
import java.util.Map;

import com.bank.dao.ReportDAO;
import com.bank.dao.ReportDAOImpl;

public class ReportServiceImpl implements ReportService {

    private final ReportDAO reportDAO;

    public ReportServiceImpl() {
        reportDAO = new ReportDAOImpl();
    }

    @Override
    public int getTotalCustomers() {
        return reportDAO.getTotalCustomers();
    }

    @Override
    public int getTotalAccounts() {
        return reportDAO.getTotalAccounts();
    }

    @Override
    public BigDecimal getTotalBankBalance() {
        return reportDAO.getTotalBankBalance();
    }

    @Override
    public Map<String, Integer> getCustomerWiseAccounts() {
        return reportDAO.getCustomerWiseAccounts();
    }
}