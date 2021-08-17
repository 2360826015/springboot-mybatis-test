package com.liuwohe.repository;

import com.liuwohe.entity.Employee;
import com.liuwohe.entity.Select;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee getOne(Integer id);

    List<Employee> getAll();

    List<Employee> getChooseed(Select select);

}