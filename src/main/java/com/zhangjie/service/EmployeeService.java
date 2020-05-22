package com.zhangjie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangjie.bean.Employee;
import com.zhangjie.dao.EmployeeMapper;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getEmployeeById(Integer id){
        return employeeMapper.getEmployeeById(id);
    }

}
