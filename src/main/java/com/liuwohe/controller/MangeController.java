package com.liuwohe.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import com.liuwohe.entity.Employee;
import com.liuwohe.entity.Organization;
import com.liuwohe.entity.Select;
import com.liuwohe.service.EmpService;
import com.liuwohe.service.OrgService;
import com.liuwohe.utils.EasyPoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MangeController {

    @Autowired
    private OrgService orgService;

    @Autowired
    private EmpService empService;

    /**
     * 进入主页面
     */
    @GetMapping("/index")
    public String getEmp(Model model){
//        //得到部门列表
//        List<Object> orgTree = orgService.getOrgTree();
//        model.addAttribute("orgTree",orgTree);
        //得到员工列表
        List<Employee> empList = empService.getEmpList();
        model.addAttribute("empList",empList);
        return "emp/list";
    }

    /**
     * 返回部门列表
     * */
    @ResponseBody
    @PostMapping("/index")
    public List<Organization> getOrgTree(){
        List<Organization> orgTree = orgService.selectAll();
        return orgTree;
    }

    /**
     * 条件查询
     * @return
     */
    @ResponseBody
    @GetMapping("/select")
    public List<Employee> selectByChoose(Select select){
        List<Employee> empList = empService.selectByChoose(select);
        return empList;
    }



    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    public String toAddPage(Model model){
        List<Organization> orgList = orgService.selectAll();
        model.addAttribute("orgList",orgList);
        return "emp/add";
    }

    /**
     * 执行添加后跳转到主页面
     */
    @PostMapping("/add")
    public String AddToList(Employee employee){
        empService.insert(employee);
        return "redirect:/index";
    }


    /**
     * 跳转到修改页面,回显信息
     */
    @GetMapping("/update/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model){
        //部门列表回显
        List<Organization> orgList = orgService.selectAll();
        model.addAttribute("orgList",orgList);
        //员工信息回显
        Employee employee = empService.upDate(id);
        model.addAttribute("emp",employee);
        return "emp/update";
    }


    /**
     * 监听修改请求,获得数据后执行修改返回主页
     */
    @PostMapping("/update")
    public String upDate(Employee employee){
        empService.upDateMsg(employee);
        System.out.println(employee);
        return "redirect:/index";
    }


    /**
     * 删除后跳转到主页面
     */
    @PostMapping("/delete/{id}")
    public String deletEmp(@PathVariable("id") Integer id){
        empService.delete(id);
        return "redirect:/index";
    }


    /**
     * 导出数据到表格
     * */
    @ResponseBody
    @GetMapping("/download")
    public void downLoad(HttpServletResponse response){
        //模拟从数据库获取需要导出的数据,实战换为数据访问层返回的结果就行。
        //得到员工列表
        List<Employee> empList = empService.getEmpList();
        //导出操作
        EasyPoiExcelUtil.exportExcel(empList,"员工信息统计","员工信息",Employee.class,"员工信息统计表.xls",response);
    }
    @ResponseBody
    @PostMapping("/upload")
    public String upLoad(@RequestParam("file") MultipartFile file){

        List<Employee> empList = EasyPoiExcelUtil.importExcel(file, 1, 1, Employee.class);
        System.out.println(empList);
        empService.insert((Employee) empList);
        return "success";
    }

}
