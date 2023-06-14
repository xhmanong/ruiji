package com.ecust.mapper;

import com.ecust.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author solang
 * @date 2023-06-11 10:50
 */
@Mapper
public interface AddressBookMapper {
    List<AddressBook> list();

    void setDefault0();

    void setDefault1(AddressBook addressBook);

    void insert(AddressBook addressBook);

    AddressBook getByDefault();

    AddressBook getById(Long addressBookId);
}
