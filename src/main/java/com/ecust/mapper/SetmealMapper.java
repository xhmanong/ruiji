package com.ecust.mapper;

import com.ecust.dto.SetmealDto;
import com.ecust.pojo.Setmeal;
import com.ecust.pojo.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-08 8:54
 */
@Mapper
public interface SetmealMapper {
    List<Setmeal> page(String name);

    void add(SetmealDto setmealDto);

    void addWithDish(SetmealDish setmealDish);

    void delete(Long[] ids);

    void deleteWithDish(Long[] ids);

    void changeStatus(Integer status, Long[] ids);

    Setmeal getById(Long id);

    List<SetmealDish> getByIdWithDish(Long id);

    void update(SetmealDto setmealDto);

    List<Setmeal> list(Long categoryId,Integer status);
}
