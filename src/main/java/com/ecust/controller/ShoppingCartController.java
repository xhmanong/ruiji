package com.ecust.controller;

import com.ecust.pojo.R;
import com.ecust.pojo.ShoppingCart;
import com.ecust.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 10:26
 */
@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/list")
    public R list(){
        List<ShoppingCart> shoppingCartList=shoppingCartService.list();
        return R.success(shoppingCartList);
    }
    @PostMapping("/add")
    public R add(HttpServletRequest request,@RequestBody ShoppingCart shoppingCart){
        shoppingCartService.add(request,shoppingCart);
        return R.success("添加成功");
    }
    @PostMapping("/sub")
    public R sub(@RequestBody ShoppingCart shoppingCart){
        log.info("接收到的数据是:{}",shoppingCart);
        shoppingCartService.sub(shoppingCart);
        return R.success("减少数量成功");
    }

    @DeleteMapping("/clean")
    public R clean(){
        shoppingCartService.clean();
        return R.success("清空成功");
    }


}
