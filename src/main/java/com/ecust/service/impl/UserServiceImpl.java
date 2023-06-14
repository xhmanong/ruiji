package com.ecust.service.impl;

import com.ecust.mapper.UserMapper;
import com.ecust.pojo.User;
import com.ecust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author solang
 * @date 2023-06-08 16:40
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User find(String phone) {
        return userMapper.find(phone);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
