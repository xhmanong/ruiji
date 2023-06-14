package com.ecust.service;

import com.ecust.pojo.AddressBook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author solang
 * @date 2023-06-11 10:49
 */
@Service
public interface AddressBookService {
    List<AddressBook> list();



    void setDefault(HttpServletRequest request,AddressBook addressBook);

    void addAddress(HttpServletRequest request, AddressBook addressBook);

    AddressBook getDefault();
}
