package com.study.com.workbench.service.Imp;

import com.study.com.settings.domain.Customer;
import com.study.com.utils.SqlSessionUtil;
import com.study.com.workbench.dao.CustomerDao;
import com.study.com.workbench.service.CustomerService;

import java.util.List;

public class CustomerServiceIpl implements CustomerService {
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    @Override
    public List<Customer> getCustomerName(String name) {
        List<Customer> customerList = customerDao.getCustomerName(name);
        return customerList;
    }
}
