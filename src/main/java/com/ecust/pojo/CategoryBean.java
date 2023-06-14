package com.ecust.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-06 14:54
 */
@Data
public class CategoryBean {
    private Long total;
    private List<Category> records;
}
