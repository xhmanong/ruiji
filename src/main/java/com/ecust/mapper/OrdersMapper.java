package com.ecust.mapper;

import com.ecust.pojo.OrderDetail;
import com.ecust.pojo.Orders;
import com.ecust.pojo.OrdersBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 17:22
 */
@Mapper
public interface OrdersMapper {

    void insertDetail(OrderDetail orderDetail);

    void insert(Orders order);

    List<Orders> list(String number, LocalDateTime beginTime, LocalDateTime endTime);

    void updateById(Orders orders);

    List<Orders> userPage();

    List<OrderDetail> getByOrderId(Long id);
}
