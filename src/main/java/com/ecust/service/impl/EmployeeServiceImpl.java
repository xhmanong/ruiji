package com.ecust.service.impl;

import com.ecust.mapper.EmployeeMapper;
import com.ecust.pojo.Employee;
import com.ecust.pojo.PageBean;
import com.ecust.service.EmployeeService;
import com.ecust.utils.IdGenerator16Bit;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.downgoon.snowflake.Snowflake;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-02 16:54
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee login(String username) {
        return employeeMapper.login(username);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page,pageSize);
        List<Employee> employeeList=employeeMapper.page(name);
        Page<Employee> p=(Page<Employee>) employeeList;
        PageBean pageBean=new PageBean();
        pageBean.setTotal(p.getTotal());
        pageBean.setRecords(p.getResult());
        return pageBean;
    }

    @Override
    public void add(Employee employee) {

        employee.setId(IdGenerator16Bit.generateId());
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeMapper.add(employee);
    }

    @Override
    public void changeStatus(Employee employee) {
        employeeMapper.changeStatus(employee);
    }

    @Override
    public Employee getInf(Integer id) {

        return employeeMapper.getInf(id);
    }
}
