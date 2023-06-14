package com.ecust.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 22:25
 */
@Data
public class OrdersBean {
    private Long total;
    private List<Orders> records;
}
