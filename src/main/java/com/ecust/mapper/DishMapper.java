package com.ecust.mapper;

import com.ecust.dto.DishDto;
import com.ecust.pojo.Dish;
import com.ecust.pojo.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-07 9:30
 */
@Mapper
public interface DishMapper {

    List<Dish> page(String name);

    void add(DishDto dishDto);

    void addFlavor(DishFlavor dishFlavor);

    void deleteById(Long[] ids);

    void deleteByIdWithDlavors(Long[] ids);

    void updateStatus(Integer status, Long[] ids);

    Dish getById(Long id);

    List<DishFlavor> getByIdWithFlavor(Long id);

    void update(DishDto dishDto);


    List<Dish> listById(Long categoryId,String name);
}
