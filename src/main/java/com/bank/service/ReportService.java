package com.bank.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ReportService {

    int getTotalCustomers();

    int getTotalAccounts();

    BigDecimal getTotalBankBalance();

    Map<String, Integer> getCustomerWiseAccounts();

}