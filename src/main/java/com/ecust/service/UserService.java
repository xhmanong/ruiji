package com.ecust.service;

import com.ecust.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author solang
 * @date 2023-06-08 16:40
 */
@Service
public interface UserService {
    User find(String phone);

    void insert(User user);
}
