package com.ecust.mapper;

import com.ecust.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-06 14:30
 */
@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    List<Category> page();
    @Update("update category set name=#{name},sort=#{sort},update_time=now(),update_user=#{updateUser} where id=#{id}")
    void update(Category category);
    @Delete("delete from category where id=#{id}")
    void deleteById(Long id);
    @Insert("insert into category(id,type,name,sort,create_time,update_time,create_user,update_user) " +
            "value(#{id},#{type},#{name},#{sort},now(),now(),#{createUser},#{updateUser})")
    void add(Category category);

    List<Category> list(Integer type);
    @Select("select name from category where id=#{categoryId}")
    String getName(Long categoryId);
}
