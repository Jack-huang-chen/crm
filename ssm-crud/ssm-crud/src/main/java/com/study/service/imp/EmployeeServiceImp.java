package com.study.service.imp;

import com.study.dao.EmployeeMapper;
import com.study.domain.Employee;
import com.study.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {

        return employeeMapper.selectByExampleWithDept(null);
    }
}
