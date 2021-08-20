package com.liuwohe.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.liuwohe.entity.Employee;
import com.liuwohe.entity.Organization;
import com.liuwohe.entity.Select;
import com.liuwohe.service.EmpService;
import com.liuwohe.service.OrgService;
import com.liuwohe.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MangeController {

    @Autowired
    private OrgService orgService;

    @Autowired
    private EmpService empService;
    private IExcelVerifyHandler MyExcelverifiyUtils;

    /**
     * 进入主页面
     */
    @GetMapping("/index")
    public String getEmp(Model model) {
//        //得到部门列表
//        List<Object> orgTree = orgService.getOrgTree();
//        model.addAttribute("orgTree",orgTree);
        //得到员工列表
        List<Employee> empList = empService.getEmpList();
        model.addAttribute("empList", empList);
        return "emp/list";
    }

    /**
     * 返回部门列表
     */
    @ResponseBody
    @PostMapping("/index")
    public List<Organization> getOrgTree() {
        List<Organization> orgTree = orgService.selectAll();
        return orgTree;
    }

    /**
     * 条件查询
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/select")
    public List<Employee> selectByChoose(Select select) {
        List<Employee> empList = empService.selectByChoose(select);
        return empList;
    }


    /**
     * 跳转到添加页面
     */
    @GetMapping("/add")
    public String toAddPage(Model model) {
        List<Organization> orgList = orgService.selectAll();
        model.addAttribute("orgList", orgList);
        return "emp/add";
    }

    /**
     * 执行添加后跳转到主页面
     */
    @PostMapping("/add")
    public String AddToList(Employee employee) {
        empService.insert(employee);
        return "redirect:/index";
    }


    /**
     * 跳转到修改页面,回显信息
     */
    @GetMapping("/update/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        //部门列表回显
        List<Organization> orgList = orgService.selectAll();
        model.addAttribute("orgList", orgList);
        //员工信息回显
        Employee employee = empService.upDate(id);
        model.addAttribute("emp", employee);
        return "emp/update";
    }


    /**
     * 监听修改请求,获得数据后执行修改返回主页
     */
    @PostMapping("/update")
    public String upDate(Employee employee) {
        empService.upDateMsg(employee);
        System.out.println(employee);
        return "redirect:/index";
    }


    /**
     * 删除后跳转到主页面
     */
    @PostMapping("/delete/{id}")
    public String deletEmp(@PathVariable("id") Integer id) {
        empService.delete(id);
        return "redirect:/index";
    }


    /**
     * 导出数据到表格
     */
    @ResponseBody
    @GetMapping("/download")
    public void downLoad(Select select,HttpServletResponse response) throws IOException {
        //模拟从数据库获取需要导出的数据,实战换为数据访问层返回的结果就行。
        //得到员工列表
        List<Employee> empList = empService.selectByChoose(select);
        System.out.println("搜索数据="+select);
        //导出操作
        ExcelUtils.exportExcel(empList, "员工信息统计", "员工信息", Employee.class, "员工信息统计表", response);
    }


    //导入操作设置实体注解校验和自定义的校验规则校验
    @PostMapping("/upload")
    public Object upLoad(@RequestParam("file") MultipartFile file, HttpServletResponse resp) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //导入的基本配置
        ImportParams params = new ImportParams();
        //表头一行
        params.setHeadRows(1);
        //标题一行
        params.setTitleRows(1);
        //代表导入这里是需要验证的（根据字段上的注解校验）
        params.setNeedVerify(true);
        //开启自定义的校验
        params.setVerifyHandler(MyExcelverifiyUtils);
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(file.getInputStream(), Employee.class, params);
        //导入成功的数据
        List<Employee> list = result.getList();
        empService.upLoad(list);
        //失败结果集
        List<Employee> failList = result.getFailList();
        System.out.println(failList);
        //拿到导出失败的工作簿
        Workbook failWorkbook = result.getFailWorkbook();
        //验证是否有失败的数据
        if (result.isVerfiyFail()) {
            ServletOutputStream fos = resp.getOutputStream();
            //mime类型
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("content-Type", "application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户数据表","UTF-8") + ".xls");
            //拿到错误的结果写入到输出中
            failWorkbook.write(resp.getOutputStream());
            fos.flush();
            fos.close();
        }
        return failList;
    }
}
