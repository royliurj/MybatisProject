package com.roy.sm.service;

import com.roy.sm.dao.DepartmentMapper;
import com.roy.sm.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public Department getDept(){
        return departmentMapper.getDeptById(1);
    }
}
