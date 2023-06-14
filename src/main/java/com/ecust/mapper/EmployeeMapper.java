package com.ecust.mapper;

import com.ecust.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-02 16:55
 */
@Mapper
public interface EmployeeMapper {
    @Select("select * from employee where username=#{username}")
    Employee login(String username);

    List<Employee> page(String name);

    void add(Employee employee);

    void changeStatus(Employee employee);

    Employee getInf(Integer id);
}
