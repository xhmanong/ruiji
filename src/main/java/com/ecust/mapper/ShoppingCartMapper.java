package com.ecust.mapper;

import com.ecust.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 10:28
 */
@Mapper
public interface ShoppingCartMapper {
    ShoppingCart select(ShoppingCart shoppingCart);

    void insert(ShoppingCart shoppingCart);

    void update(ShoppingCart sc);

    List<ShoppingCart> listById(Long userId);

    void delete();


    BigDecimal count(Long user);

    List<ShoppingCart> list();

    void deleteById(ShoppingCart shoppingCart);
}
