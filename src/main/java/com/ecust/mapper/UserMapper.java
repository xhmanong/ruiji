package com.ecust.mapper;

import com.ecust.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author solang
 * @date 2023-06-08 16:41
 */
@Mapper
public interface UserMapper {
    User find(String phone);

    void insert(User user);
}
