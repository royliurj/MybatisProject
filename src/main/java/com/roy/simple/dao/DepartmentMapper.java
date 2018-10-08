package com.roy.simple.dao;

import com.roy.simple.model.Department;

public interface DepartmentMapper {
    Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);
}
