package com.ecust.service;

import com.ecust.pojo.Category;
import com.ecust.pojo.CategoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-06 14:29
 */
@Service
public interface CategoryService {
    CategoryBean page(Integer page, Integer pageSize);

    void update(Category category);

    void delete(Long id);

    void add(Category category);


    List<Category> list(Integer type);
}
