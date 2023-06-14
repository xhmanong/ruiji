package com.ecust.service.impl;

import com.ecust.mapper.AddressBookMapper;
import com.ecust.pojo.AddressBook;
import com.ecust.service.AddressBookService;
import com.ecust.utils.IdGenerator16Bit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-11 10:49
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public List<AddressBook> list() {

        return addressBookMapper.list();
    }

    @Transactional
    @Override
    public void setDefault(HttpServletRequest request,AddressBook addressBook) {
        addressBookMapper.setDefault0();
        addressBook.setUpdateUser((Long) request.getSession().getAttribute("user"));
        addressBookMapper.setDefault1(addressBook);
    }

    @Override
    public void addAddress(HttpServletRequest request, AddressBook addressBook) {
        addressBook.setId(IdGenerator16Bit.generateId());
        addressBook.setCreateUser((Long) request.getSession().getAttribute("user"));
        addressBook.setUpdateUser((Long) request.getSession().getAttribute("user"));
        addressBook.setUserId((Long) request.getSession().getAttribute("user"));
        addressBookMapper.insert(addressBook);


    }

    @Override
    public AddressBook getDefault() {
        return addressBookMapper.getByDefault();
    }


}
