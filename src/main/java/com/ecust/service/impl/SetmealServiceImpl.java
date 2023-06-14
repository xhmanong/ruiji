package com.ecust.service.impl;

import com.ecust.dto.SetmealDto;
import com.ecust.mapper.CategoryMapper;
import com.ecust.mapper.SetmealMapper;
import com.ecust.pojo.Setmeal;
import com.ecust.pojo.SetmealBean;
import com.ecust.pojo.SetmealDish;
import com.ecust.service.SetmealService;
import com.ecust.utils.IdGenerator16Bit;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Line;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2023-06-08 8:54
 */
@Slf4j
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /*
    * 分页查询
    * */
    @Override
    public SetmealBean page(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page,pageSize);
        List<Setmeal>  setmeals=setmealMapper.page(name);
        List<SetmealDto> setmealDtos=setmeals.stream().map((item)->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            String categoryName=categoryMapper.getName(item.getCategoryId());
            setmealDto.setCategoryName(categoryName);
            return setmealDto;
        }).collect(Collectors.toList());
        Page<Setmeal> p=(Page<Setmeal>) setmeals;
        SetmealBean setmealBean=new SetmealBean();
        setmealBean.setTotal(p.getTotal());
        setmealBean.setRecords(setmealDtos);
        return setmealBean;
    }

    /*
    * 新增套餐
    * */
    @Transactional
    @Override
    public void add(HttpServletRequest request, SetmealDto setmealDto) {
        setmealDto.setCreateUser((Long) request.getSession().getAttribute("employee"));
        setmealDto.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        setmealDto.setId(IdGenerator16Bit.generateId());
        setmealMapper.add(setmealDto);
        setmealDto.getSetmealDishes().stream().map((item)->{
            log.info("666");
            SetmealDish setmealDish = new SetmealDish();
            BeanUtils.copyProperties(item,setmealDish);
            setmealDish.setId(IdGenerator16Bit.generateId());
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDish.setCreateUser((Long) request.getSession().getAttribute("employee"));
            setmealDish.setUpdateUser((Long) request.getSession().getAttribute("employee"));
            setmealMapper.addWithDish(setmealDish);
            return setmealDish;
        }).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void delete(Long[] ids) {
        setmealMapper.delete(ids);
        setmealMapper.deleteWithDish(ids);
    }

    @Override
    public void changeStatus(Integer status, Long[] ids) {
        setmealMapper.changeStatus(status,ids);
    }

    @Transactional
    @Override
    public SetmealDto getById(Long id) {
        SetmealDto setmealDto=new SetmealDto();
        Setmeal setmeal=setmealMapper.getById(id);
        List<SetmealDish> setmealDishes=setmealMapper.getByIdWithDish(id);
        BeanUtils.copyProperties(setmeal,setmealDto);
        setmealDto.setSetmealDishes(setmealDishes);
        return setmealDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(HttpServletRequest request, SetmealDto setmealDto) {
        setmealDto.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        setmealMapper.update(setmealDto);
        setmealDto.getSetmealDishes().stream().map((item)->{
            SetmealDish setmealDish = new SetmealDish();
            //先删除再插入
            setmealDish.setId(IdGenerator16Bit.generateId());
            setmealDish.setDishId(item.getDishId());
            setmealDish.setCopies(item.getCopies());
            setmealDish.setSetmealId(setmealDto.getId());
            setmealDish.setName(item.getName());
            setmealDish.setPrice(item.getPrice());
            setmealDish.setUpdateUser((Long) request.getSession().getAttribute("employee"));
            setmealDish.setCreateUser((Long) request.getSession().getAttribute("employee"));
            Long[] id={setmealDto.getId()};
            setmealMapper.deleteWithDish(id);
            setmealMapper.addWithDish(setmealDish);
            return setmealDish;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Setmeal> list(Long categoryId,Integer status) {
        return setmealMapper.list(categoryId,status);
    }
}
