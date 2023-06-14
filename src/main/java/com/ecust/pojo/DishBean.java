package com.ecust.pojo;

import com.ecust.dto.DishDto;
import lombok.Data;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-07 9:37
 */
@Data
public class DishBean {
    private Long total;
    private List<DishDto> records;
}
