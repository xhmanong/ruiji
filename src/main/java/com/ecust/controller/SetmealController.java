package com.ecust.controller;

import com.ecust.dto.SetmealDto;
import com.ecust.pojo.R;
import com.ecust.pojo.Setmeal;
import com.ecust.pojo.SetmealBean;
import com.ecust.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-08 8:51
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    /*
    * 分页查询
    * */
    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "1") Integer page,
                  @RequestParam(defaultValue = "10") Integer pageSize,
                  String name){
        SetmealBean setmealBean=setmealService.page(page,pageSize,name);
        return R.success(setmealBean);
    }
    /*
    * 新增套餐
    * */
    @PostMapping
    public R add(HttpServletRequest request, @RequestBody SetmealDto setmealDto){

        setmealService.add(request,setmealDto);
        return R.success("新增成功");

    }

    /*
    * 批量删除
    * */
    @DeleteMapping
    public R delete(Long[] ids){
        setmealService.delete(ids);
        return R.success("删除成功");
    }

    /*
    * 批量起售/停售
    * */
    @PostMapping("/status/{status}")
    public R changeStatus(@PathVariable Integer status,Long[] ids){
        setmealService.changeStatus(status,ids);
        return R.success("操作成功");
    }

    /*
    * 修改回显
    * */
    @GetMapping("/{id}")
    public R getById(@PathVariable Long id){
        SetmealDto setmealDto=setmealService.getById(id);
        return R.success(setmealDto);
    }
    /*
    * 修改保存
    * */
    @PutMapping
    public R update(HttpServletRequest request,@RequestBody SetmealDto setmealDto){
        setmealService.update(request,setmealDto);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R list(Long categoryId,Integer status){
        List<Setmeal> setmeals=setmealService.list(categoryId,status);
        return R.success(setmeals);
    }
}
