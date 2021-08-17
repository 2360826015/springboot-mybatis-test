package com.liuwohe.service.Impl;

import com.liuwohe.repository.EmployeeMapper;
import com.liuwohe.entity.Employee;
import com.liuwohe.entity.Select;
import com.liuwohe.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("all")
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getEmpList() {
        return  employeeMapper.getAll();
    }

    @Override
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }


    @Override
    public void delete(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    //回显员工数据
    public Employee upDate(Integer id){
      return  employeeMapper.selectByPrimaryKey(id);
    }

    //更新员工数据
    @Override
    public void upDateMsg(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public List<Employee> selectByChoose(Select select) {
        return employeeMapper.getChooseed(select);
    }

}
