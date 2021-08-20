package com.liuwohe.service;

import com.liuwohe.entity.Employee;
import com.liuwohe.entity.Select;

import java.util.List;

public interface EmpService {
    List<Employee> getEmpList();

    void insert(Employee employee);

    void delete(Integer id);

    Employee upDate(Integer id);

    void upDateMsg(Employee employee);

    List<Employee> selectByChoose(Select select);

    void upLoad(List<Employee> empList);
}
