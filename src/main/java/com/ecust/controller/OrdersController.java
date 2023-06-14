package com.ecust.controller;

import com.ecust.dto.OrdersDto;
import com.ecust.pojo.*;
import com.ecust.service.Orderservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 17:19
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private Orderservice orderservice;


    @PostMapping("/submit")
    public R submit(HttpServletRequest request, @RequestBody Orders orders){
        log.info("接收到的数据为:{}",orders);
        orderservice.submit(request,orders);
        return R.success("支付成功");
    }

    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "1")Integer page,
                  @RequestParam(defaultValue = "10")Integer pageSize,
                  String number,
                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime beginTime,
                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime endTime){
        log.info("begin:{},end:{}",beginTime,endTime);
    OrdersBean ordersBean= orderservice.page(page,pageSize,number,beginTime,endTime);
    return R.success(ordersBean);
    }
    @PutMapping
    public R changeStatus(@RequestBody Orders orders){
        log.info("接收到的数据是：{}",orders);
        orderservice.changeStatus(orders);
        return R.success("更新状态成功");
    }

    @GetMapping("/userPage")
    public R userPage(@RequestParam(defaultValue = "1")Integer page,
                      @RequestParam(defaultValue = "1")Integer pageSize){
        OrdersPage ordersPage=orderservice.userPage(page,pageSize);
        return R.success(ordersPage);
    }

    @PostMapping("/again")
    public R again(HttpServletRequest request,@RequestBody Orders orders){
        log.info("再来一单接收到的数据为:{}",orders);
        orderservice.again(request,orders);
        return R.success("");
    }
}
