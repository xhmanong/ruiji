package com.ecust.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-04 14:23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageBean {
    private Long total;
    private List<Employee> records;
}
