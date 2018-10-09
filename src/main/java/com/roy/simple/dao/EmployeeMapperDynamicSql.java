package com.roy.simple.dao;

import com.roy.simple.model.Employee;

import java.util.List;

public interface EmployeeMapperDynamicSql {

    public List<Employee> getEmpsByConditionIf(Employee employee);
    public List<Employee> getEmpsByConditionIfTrim(Employee employee);
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    public void updateEmp(Employee employee);
}
