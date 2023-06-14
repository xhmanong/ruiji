package com.ecust.service;

import com.ecust.pojo.Orders;
import com.ecust.pojo.OrdersBean;
import com.ecust.pojo.OrdersPage;
import com.ecust.pojo.ShoppingCart;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 17:20
 */
@Service
public interface Orderservice {
    void submit(HttpServletRequest request, Orders orders);

    OrdersBean page(Integer page, Integer pageSize, String number, LocalDateTime beginTime, LocalDateTime endTime);

    void changeStatus(Orders orders);

    OrdersPage userPage(Integer page, Integer pageSize);


    void again(HttpServletRequest request,Orders orders);
}
