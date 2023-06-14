package com.ecust.pojo;

import com.ecust.dto.OrdersDto;
import lombok.Data;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-13 10:15
 */
@Data
public class OrdersPage {
    private Long total;
    private List<OrdersDto> records;
}
