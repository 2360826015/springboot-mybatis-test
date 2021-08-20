package com.liuwohe.service;

import com.liuwohe.entity.Employee;

public interface CheckEmpIdService {
    Employee findOneById(Integer id);
}
