package com.ecust.service;

import com.ecust.pojo.ShoppingCart;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 10:27
 */
@Service
public interface ShoppingCartService {
    void add(HttpServletRequest request, ShoppingCart shoppingCart);


    List<ShoppingCart> list();

    void sub(ShoppingCart shoppingCart);

    void clean();
}
