package com.ecust.service;

import com.ecust.dto.DishDto;
import com.ecust.pojo.Dish;
import com.ecust.pojo.DishBean;
import com.ecust.pojo.DishFlavor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-07 9:28
 */
@Service
public interface DishService {
    DishBean page(Integer page, Integer pageSize, String name);

    void add(DishDto dishDto);

    void addFlavor(DishFlavor dishFlavor);

    void deleteById(Long[] ids);

    void updateStatus(Integer status, Long[] ids);

    DishDto getById(Long id);

    void update(DishDto dishDto);

    List<DishDto> listById(Long categoryId,String name);
}
