package com.roy.simple.dao;

import com.roy.simple.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmployeeMapper {
    Employee getEmpById(Integer id);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    boolean deleteEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
    Employee getEmpByMap(Map<String,Object> map);
}
