package com.ecust.controller;

import com.ecust.mapper.EmployeeMapper;
import com.ecust.pojo.Category;
import com.ecust.pojo.Employee;
import com.ecust.pojo.PageBean;
import com.ecust.pojo.R;
import com.ecust.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author solang
 * @date 2023-06-02 16:54
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
/*
* 分页查询
* */
    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "1") Integer page,
                  @RequestParam(defaultValue = "10") Integer pageSize,
                  String name
                  ){
        PageBean pageBean=employeeService.page(page,pageSize,name);
        return R.success(pageBean);

    }
    /**
     * 添加员工
     */
    @PostMapping
    public R add(HttpServletRequest request, @RequestBody Employee employee){
        employee.setCreateUser((Long) request.getSession().getAttribute("employee"));
        log.info("session是：{}",employee.getCreateUser());
        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.add(employee);
        return R.success("添加成功");
    }

    @PutMapping
    public R changeStatus(@RequestBody Employee employee){
        log.info("禁用接收到的数据是:{}",employee);
        employeeService.changeStatus(employee);
        return R.success("禁用成功");

    }

    @GetMapping("/{id}")
    public R getInf(@PathVariable Integer id){
        Employee employee=employeeService.getInf(id);
        return R.success(employee);
    }



}
