package com.study.com.workbench.service;

import com.study.com.settings.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomerName(String name);
}
