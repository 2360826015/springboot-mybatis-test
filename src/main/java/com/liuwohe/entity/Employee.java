package com.liuwohe.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Employee implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @Excel(name = "员工编号")
    private Integer id;

    @Excel(name = "所属组织编号",orderNum = "1")
    private String orgId;

    @Excel(name = "性别",orderNum = "3")
    private String sex;

    @Excel(name = "出生日期",exportFormat = "yyyy-MM-dd",orderNum = "4")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Excel(name = "员工姓名",orderNum = "2")
    private String name;

    @Excel(name = "年龄",orderNum = "5")
    private Integer age;

    @Excel(name = "工资",orderNum = "6")
    private Double salary;

    private Organization organization;
}