package com.roy.sm.controller;

import com.roy.sm.model.Employee;
import com.roy.sm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World111";
    }

    @GetMapping("/emp")
    public Employee emp(){
        return employeeService.getEmpById(3);
    }
}
