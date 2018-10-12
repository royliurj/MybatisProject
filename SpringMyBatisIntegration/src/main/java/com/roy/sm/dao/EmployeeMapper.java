package com.roy.sm.dao;

import com.roy.sm.model.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeMapper {
    Employee getEmpById(Integer id);

}
