package com.zhangjie.controller;

import com.zhangjie.bean.Employee;
import com.zhangjie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getEmployeeById")
    @ResponseBody
    public String getEmployeeById(){
        Employee employee = employeeService.getEmployeeById(1);
        return employee.getLastName();
    	
    }

}
