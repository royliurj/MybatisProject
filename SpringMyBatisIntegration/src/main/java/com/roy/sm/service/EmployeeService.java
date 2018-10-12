package com.roy.sm.service;

import com.roy.sm.dao.EmployeeMapper;
import com.roy.sm.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getEmpById(Integer id){
        return employeeMapper.getEmpById(id);
    }
}
