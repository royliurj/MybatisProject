package com.roy.simple.dao;

import com.roy.simple.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSql {

    public List<Employee> getEmpsByConditionIf(Employee employee);
    public List<Employee> getEmpsByConditionIfTrim(Employee employee);
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    public void updateEmp(Employee employee);
    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);
    public void admEmps(@Param("emps") List<Employee> emps);

    public List<Employee> getEmpsInnerParameter(Employee employee);
    public List<Employee> getEmpsBind(Employee employee);
}
