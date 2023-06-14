package com.ecust.controller;

import com.ecust.pojo.Category;
import com.ecust.pojo.CategoryBean;
import com.ecust.pojo.R;
import com.ecust.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Line;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-06 14:29
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "1" )Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize){
        CategoryBean categoryBean=categoryService.page(page,pageSize);
        return R.success(categoryBean);

    }

    /*
    * 修改菜品分类
    * */
    @PutMapping
    public R update(HttpServletRequest request, @RequestBody Category category){


        log.info("接收到的数据是:{}",category.toString());
        category.setId((Long) request.getSession().getAttribute("employee"));
        categoryService.update(category);
        return R.success("修改成功");

    }

    /**
     * 删除分类
     * @param ids
     * @return
     */

    @DeleteMapping
    public R deleteById(Long ids){
        log.info("接收到的id是：{}",ids);
        categoryService.delete(ids);
        return R.success("删除成功");

    }

    /*
     * 新增菜品套餐分类
     * */
    @PostMapping
    public R add(HttpServletRequest request,@RequestBody Category category){
        category.setCreateUser((Long) request.getSession().getAttribute("employee"));
        category.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        categoryService.add(category);
        return R.success("新增成功");
    }

    @GetMapping("/list")
    public R list(Integer type){
        log.info("接收到的type的数据是:{}",type);
        List<Category> categoryList=categoryService.list(type);
        return R.success(categoryList);
    }



}
