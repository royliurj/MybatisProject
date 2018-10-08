package com.roy.simple.dao;

import com.roy.simple.model.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    Employee getEmpById(Integer id);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    boolean deleteEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
    Employee getEmpByMap(Map<String,Object> map);

    List<Employee> getEmpList();
    Map<String,Object> getEmpMapById(Integer id);

    //使用@MapKey注解设置主键
    @MapKey("id")
    Map<String,Employee> getEmpMaps();

    public List<Employee> getEmpsByDeptId(Integer deptId);

}
