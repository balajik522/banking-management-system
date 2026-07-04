package com.bank.dao;

import java.math.BigDecimal;
import java.util.Map;

public interface ReportDAO {

    int getTotalCustomers();

    int getTotalAccounts();

    BigDecimal getTotalBankBalance();

    Map<String, Integer> getCustomerWiseAccounts();

}