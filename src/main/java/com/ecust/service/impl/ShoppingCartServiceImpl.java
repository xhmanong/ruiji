package com.ecust.service.impl;

import com.ecust.mapper.ShoppingCartMapper;
import com.ecust.pojo.ShoppingCart;
import com.ecust.service.ShoppingCartService;
import com.ecust.utils.IdGenerator16Bit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-12 10:28
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public void add(HttpServletRequest request, ShoppingCart shoppingCart) {
        ShoppingCart sc=shoppingCartMapper.select(shoppingCart);
        if(sc==null){
            shoppingCart.setId(IdGenerator16Bit.generateId());
            shoppingCart.setUserId((Long) request.getSession().getAttribute("user"));
            shoppingCart.setNumber(1);
            shoppingCartMapper.insert(shoppingCart);
        }else{
            sc.setNumber(sc.getNumber()+1);
            shoppingCartMapper.update(sc);
        }

    }

    @Override
    public List<ShoppingCart> list() {
        return shoppingCartMapper.list();
    }

    @Override
    public void sub(ShoppingCart shoppingCart) {
        ShoppingCart cart = shoppingCartMapper.select(shoppingCart);
        Integer number=cart.getNumber()-1;
        if(number==0){
            shoppingCartMapper.deleteById(shoppingCart);
        }else{
            cart.setNumber(number);
            shoppingCartMapper.update(cart);
        }

    }

    @Override
    public void clean() {
        shoppingCartMapper.delete();
    }
}
