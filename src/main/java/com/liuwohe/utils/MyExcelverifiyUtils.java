package com.liuwohe.utils;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.liuwohe.entity.Employee;
import com.liuwohe.service.CheckEmpIdService;
import org.springframework.beans.factory.annotation.Autowired;

public class MyExcelverifiyUtils implements IExcelVerifyHandler<Employee> {

    @Autowired
    private CheckEmpIdService checkEmpIdService;
    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        //设置默认验证为true
        ExcelVerifyHandlerResult excelVerifyHandlerResult = new ExcelVerifyHandlerResult(true);
            Employee oneById = checkEmpIdService.findOneById(employee.getId());
            //查询不为空则说明数据库中用户已存在，此次录入为重复录入
            if (oneById != null) {
                excelVerifyHandlerResult.setSuccess(false);
                excelVerifyHandlerResult.setMsg("对不起，此用户已存在，请不要重复提交");
            }
        return excelVerifyHandlerResult;
    }
}
