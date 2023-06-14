package com.ecust.service;

import com.ecust.pojo.Employee;
import com.ecust.pojo.PageBean;
import org.springframework.stereotype.Service;

/**
 * @author solang
 * @date 2023-06-02 16:54
 */
@Service
public interface EmployeeService {
    Employee login(String username);

    PageBean page(Integer page, Integer pageSize, String name);

    void add(Employee employee);

    void changeStatus(Employee employee);

    Employee getInf(Integer id);
}
