package com.ecust.dto;

import com.ecust.pojo.Dish;
import com.ecust.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-07 10:03
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
