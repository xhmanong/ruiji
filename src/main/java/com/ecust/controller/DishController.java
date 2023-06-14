package com.ecust.controller;

import com.ecust.dto.DishDto;
import com.ecust.pojo.Dish;
import com.ecust.pojo.DishBean;
import com.ecust.pojo.DishFlavor;
import com.ecust.pojo.R;
import com.ecust.service.DishService;
import com.ecust.utils.IdGenerator16Bit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2023-06-07 9:26
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /*
    * 菜品分页查询
    * */
    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "1") Integer page,
                  @RequestParam(defaultValue = "10") Integer pageSize,
                  String name){
        DishBean dishBean=dishService.page(page,pageSize,name);
        return R.success(dishBean);
    }

    /*
    * 新增菜品
    * */
    @PostMapping
    public R add(HttpServletRequest servletRequest, @RequestBody DishDto dishDto){
        log.info("接收到的数据是:{}",dishDto);

        List<DishFlavor> list=dishDto.getFlavors();
        list.stream().map(item->{
            DishFlavor dishFlavor = new DishFlavor();
            dishFlavor.setId(IdGenerator16Bit.generateId());
            dishFlavor.setDishId(dishDto.getCategoryId());
            dishFlavor.setName(item.getName());
            dishFlavor.setValue(item.getValue());
            dishFlavor.setCreateUser((Long) servletRequest.getSession().getAttribute("employee"));
            dishFlavor.setUpdateUser((Long) servletRequest.getSession().getAttribute("employee"));
            log.info("dishflavor里的数据是:{}",dishFlavor);
            dishService.addFlavor(dishFlavor);
            return dishFlavor;}).collect(Collectors.toList());

        dishDto.setId(IdGenerator16Bit.generateId());
        dishDto.setCreateUser((Long) servletRequest.getSession().getAttribute("employee"));
        dishDto.setUpdateUser((Long) servletRequest.getSession().getAttribute("employee"));
        dishDto.setStatus(1);
        dishService.add(dishDto);
        return R.success("添加成功");
    }

    /*
    * 批量删除
    * */
    @DeleteMapping
    public R deleteById(Long[] ids){

        dishService.deleteById(ids);
        return R.success("删除成功");
    }

    /*
    * 批量起售/停售
    * */
    @PostMapping("/status/{status}")
    public R updateStatus(@PathVariable Integer status,Long[] ids){
        dishService.updateStatus(status,ids);
        return R.success("修改成功");
    }

    /*
    * 修改回显
    * */
    @GetMapping("/{id}")
    public R getById(@PathVariable Long id){
        DishDto dishDto=dishService.getById(id);
        return R.success(dishDto);
    }

    /*
    * 修改菜品
    * */
    @PutMapping()
    public R update(HttpServletRequest request,@RequestBody DishDto dishDto){
        dishDto.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        dishService.update(dishDto);
        return R.success("更新成功");
    }
    /*
    * 菜品列表
    * */
    @GetMapping("/list")
    public R list(Long categoryId,String name){
        List<DishDto> dishNames=dishService.listById(categoryId,name);
        return R.success(dishNames);
    }
}
