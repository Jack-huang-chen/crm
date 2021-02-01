package com.study.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.dao.EmployeeMapper;
import com.study.domain.Employee;
import com.study.service.EmployeeService;
import com.study.service.imp.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/emps")
    public String getAllEp(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpServletRequest request){
        PageHelper.startPage(pn,5);
        List<Employee> employeeList = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(employeeList);
        request.setAttribute("pageInfo",pageInfo);

        return "list";


    }
}
