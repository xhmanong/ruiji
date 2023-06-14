package com.ecust.service.impl;

import com.ecust.mapper.CategoryMapper;
import com.ecust.pojo.Category;
import com.ecust.pojo.CategoryBean;
import com.ecust.service.CategoryService;
import com.ecust.utils.IdGenerator16Bit;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-06 14:30
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryBean page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Category> categoryList =categoryMapper.page();
        Page<Category> categoryPage=(Page<Category>) categoryList;
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setTotal(categoryPage.getTotal());
        categoryBean.setRecords(categoryPage.getResult());
        return categoryBean;


    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public void add(Category category) {
        category.setId(IdGenerator16Bit.generateId());
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }


}
