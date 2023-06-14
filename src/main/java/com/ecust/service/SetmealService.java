package com.ecust.service;

import com.ecust.dto.SetmealDto;
import com.ecust.pojo.Setmeal;
import com.ecust.pojo.SetmealBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-08 8:53
 */
@Service
public interface SetmealService {
    SetmealBean page(Integer page, Integer pageSize, String name);

    void add(HttpServletRequest request, SetmealDto setmealDto);

    void delete(Long[] ids);

    void changeStatus(Integer status, Long[] ids);

    SetmealDto getById(Long id);


    void update(HttpServletRequest request, SetmealDto setmealDto);

    List<Setmeal> list(Long categoryId, Integer status);
}
