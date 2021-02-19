package com.study.com.workbench.dao;

import com.study.com.settings.domain.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getCustomerByName(String company);

    int save(Customer customer);

    List<Customer> getCustomerName(String name);
}
