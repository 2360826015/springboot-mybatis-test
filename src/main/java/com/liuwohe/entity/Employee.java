package com.liuwohe.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Data
public class Employee implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @Excel(name = "员工编号")
    @NotNull(message = "员工编号不能为空!")
    private Integer id;

    @Excel(name = "所属组织编号",orderNum = "1")
    @NotNull(message = "组织编号不能为空!")
    private String orgId;

    @Excel(name = "性别",orderNum = "3",replace = {"男_1", "女_2"})
    @NotNull(message = "性别不能为空!")
    private String sex;

    @Excel(name = "出生日期",exportFormat = "yyyy-MM-dd",format = "yyyy-MM-dd",databaseFormat = "yyyyMMdd",orderNum = "4")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "出生日期不能为空!")
    private Date birthday;

    @Excel(name = "员工姓名",orderNum = "2")
    @NotNull(message = "员工姓名不能为空!")
    private String name;

    @Excel(name = "年龄",orderNum = "5")
    @Max(value = 65,message = "最大年龄不能超过65岁!")
    @Min(value = 18,message = "最小年龄不能小于18岁!")
    private Integer age;

    @Excel(name = "工资",orderNum = "6")
    private Double salary;

    private Organization organization;
}