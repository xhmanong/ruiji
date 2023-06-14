package com.ecust.service.impl;

import com.ecust.dto.OrdersDto;
import com.ecust.mapper.AddressBookMapper;
import com.ecust.mapper.OrdersMapper;
import com.ecust.mapper.ShoppingCartMapper;
import com.ecust.pojo.*;
import com.ecust.service.Orderservice;
import com.ecust.utils.IdGenerator16Bit;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2023-06-12 17:20
 */
@Service
public class OrdersServiceImpl implements Orderservice {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public void submit(HttpServletRequest request, Orders orders) {
        orders.setId(IdGenerator16Bit.generateId());
        orders.setNumber(IdGenerator16Bit.uuid());
        orders.setStatus(2);
        orders.setUserId((Long) request.getSession().getAttribute("user"));
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        AddressBook addressBook=addressBookMapper.getById(orders.getAddressBookId());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getDetail());
        orders.setConsignee(addressBook.getConsignee());
        orders.setUserName(orders.getPhone());
        orders.setAmount(shoppingCartMapper.count((Long) request.getSession().getAttribute("user")));
        List<ShoppingCart> shoppingCartList=shoppingCartMapper.listById((Long) request.getSession().getAttribute("user"));
        shoppingCartList.stream().map((item)->{
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(item,orderDetail);
            orderDetail.setOrderId(orders.getId());
            ordersMapper.insertDetail(orderDetail);
            return orderDetail;
        }).collect(Collectors.toList());

        //amount
        //user_name
        //根据用户查出订单细节到表中
        //删除购物车数据



        ordersMapper.insert(orders);
        shoppingCartMapper.delete();
    }

    @Override
    public OrdersBean page(Integer page, Integer pageSize, String number, LocalDateTime beginTime, LocalDateTime endTime) {
        PageHelper.startPage(page,pageSize);
        List<Orders> orders=ordersMapper.list(number,beginTime,endTime);
        Page<Orders> p=(Page<Orders>) orders;
        OrdersBean ordersBean = new OrdersBean();
        ordersBean.setTotal(p.getTotal());
        ordersBean.setRecords(p.getResult());

        return ordersBean;
    }

    @Override
    public void changeStatus(Orders orders) {
        ordersMapper.updateById(orders);
    }

    @Override
    public OrdersPage userPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Orders> ordersList=ordersMapper.userPage();
        List<OrdersDto> ordersDtos=ordersList.stream().map((item)->{
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(item,ordersDto);
            List<OrderDetail> orderDetails=ordersMapper.getByOrderId(item.getId());
            ordersDto.setOrderDetails(orderDetails);
            return ordersDto;
        }).collect(Collectors.toList());
        Page<Orders> p=(Page<Orders>) ordersList;
        OrdersPage ordersPage = new OrdersPage();
        ordersPage.setTotal(p.getTotal());
        ordersPage.setRecords(ordersDtos);
        return ordersPage;
    }

    @Override
    public void again(HttpServletRequest request,Orders orders) {
        List<OrderDetail> orderDetails = ordersMapper.getByOrderId(orders.getId());
        orderDetails.stream().map((item)->{
            ShoppingCart shoppingCart = new ShoppingCart();
            BeanUtils.copyProperties(item,shoppingCart);
            shoppingCart.setId(IdGenerator16Bit.generateId());
            shoppingCart.setUserId((Long) request.getSession().getAttribute("user"));
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);

            return shoppingCart;
        }).collect(Collectors.toList());
    }
}
