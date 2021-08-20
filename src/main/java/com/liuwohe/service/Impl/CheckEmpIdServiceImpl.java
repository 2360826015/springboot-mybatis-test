package com.liuwohe.service.Impl;

import com.liuwohe.entity.Employee;
import com.liuwohe.repository.EmployeeMapper;
import com.liuwohe.service.CheckEmpIdService;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckEmpIdServiceImpl implements CheckEmpIdService {
    @Autowired
    private EmployeeMapper empMapper;
    @Override
    public Employee findOneById(Integer id) {
        Employee employee = empMapper.selectByPrimaryKey(id);
        System.out.println(employee);
        if(employee.getId()==null){
            return null;
        }
        return employee;
    }
}
