package com.ecust.service.impl;

import com.ecust.dto.DishDto;
import com.ecust.mapper.CategoryMapper;
import com.ecust.mapper.DishMapper;
import com.ecust.pojo.Category;
import com.ecust.pojo.Dish;
import com.ecust.pojo.DishBean;
import com.ecust.pojo.DishFlavor;
import com.ecust.service.DishService;
import com.ecust.utils.IdGenerator16Bit;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2023-06-07 9:29
 */
@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public DishBean page(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<Dish> dishList = dishMapper.page(name);
        log.info("dishList的内容是:{}", dishList);

        Page<Dish> p = (Page<Dish>) dishList;
        log.info("p的内容是:{}", p);
        List<DishDto> list = p.getResult().stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            dishDto.setCategoryName(categoryMapper.getName(item.getCategoryId()));
            return dishDto;
        }).collect(Collectors.toList());
        DishBean dishBean = new DishBean();
        dishBean.setTotal(p.getTotal());
        log.info("dishBean的内容是:{}", dishBean);
        dishBean.setRecords(list);
        return dishBean;
    }

    @Override
    public void add(DishDto dishDto) {
        dishMapper.add(dishDto);
    }

    @Override
    public void addFlavor(DishFlavor dishFlavor) {
        dishMapper.addFlavor(dishFlavor);
    }

    @Transactional
    @Override
    public void deleteById(Long[] ids) {
        dishMapper.deleteById(ids);
        dishMapper.deleteByIdWithDlavors(ids);
    }

    @Override
    public void updateStatus(Integer status, Long[] ids) {
        dishMapper.updateStatus(status, ids);
    }

    @Transactional
    @Override
    public DishDto getById(Long id) {
        DishDto dishDto = new DishDto();
        Dish dish = dishMapper.getById(id);
        List<DishFlavor> dishFlavorList = dishMapper.getByIdWithFlavor(id);
        BeanUtils.copyProperties(dish, dishDto);
        dishDto.setFlavors(dishFlavorList);
        return dishDto;

    }

    @Transactional
    @Override
    public void update(DishDto dishDto) {
        dishMapper.update(dishDto);
        dishDto.getFlavors().stream().map((item) -> {
            DishFlavor dishFlavor = new DishFlavor();
            dishFlavor.setId(IdGenerator16Bit.generateId());
            dishFlavor.setDishId(dishDto.getId());
            dishFlavor.setName(item.getName());
            dishFlavor.setValue(item.getValue());
            dishFlavor.setUpdateUser(dishDto.getUpdateUser());
            dishFlavor.setCreateUser(dishDto.getCreateUser());
            Long[] id = {dishDto.getId()};
            dishMapper.deleteByIdWithDlavors(id);
            dishMapper.addFlavor(dishFlavor);
            return dishFlavor;
        }).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public List<DishDto> listById(Long categoryId, String name) {

        List<Dish> dishList = dishMapper.listById(categoryId, name);
        List<DishDto> dishDtos = dishList.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            dishDto.setCategoryName(categoryMapper.getName(categoryId));
            Long dishId = item.getId();
            dishDto.setFlavors(dishMapper.getByIdWithFlavor(dishId));
            return dishDto;
        }).collect(Collectors.toList());
        return dishDtos;

    }
}
