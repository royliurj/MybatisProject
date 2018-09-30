package com.roy.simple.dao;

import com.roy.simple.model.Employee;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    Employee getEmpAndDeptById(Integer id);

    Employee getEmpByIdStep(Integer id);
}
